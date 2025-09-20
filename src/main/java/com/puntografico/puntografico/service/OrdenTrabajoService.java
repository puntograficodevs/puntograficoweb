package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.EstadoOrdenRepository;
import com.puntografico.puntografico.repository.EstadoPagoRepository;
import com.puntografico.puntografico.repository.MedioPagoRepository;
import com.puntografico.puntografico.repository.OrdenTrabajoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional @AllArgsConstructor
public class OrdenTrabajoService {

    private final OrdenTrabajoRepository ordenTrabajoRepository;

    private final EstadoOrdenRepository estadoOrdenRepository;

    private final EstadoPagoRepository estadoPagoRepository;

    private final MedioPagoRepository medioPagoRepository;

    private final EmpleadoService empleadoService;

    public OrdenTrabajo guardar(HttpServletRequest request, Long idOrdenTrabajo) {
        OrdenTrabajo ordenTrabajo = (idOrdenTrabajo != null) ? ordenTrabajoRepository.findById(idOrdenTrabajo).get() : new OrdenTrabajo();

        boolean esCreacion = idOrdenTrabajo == null;
        int total;
        int abonado;
        int resta;

        if (esCreacion) {
            total = Integer.parseInt(request.getParameter("total"));
            abonado = Integer.parseInt(request.getParameter("abonado"));
            resta = total - abonado;
            String medioPagoIdStr = request.getParameter("medioPago.id");

            asignarValoresDelPago(ordenTrabajo, total, abonado, resta);
            asignarMedioPago(ordenTrabajo, medioPagoIdStr);
        } else if (!esCreacion && (request.getParameter("nuevoTotal")) != null) {
            Integer totalNuevo = Integer.parseInt(request.getParameter("nuevoTotal"));
            total = totalNuevo != null ? totalNuevo : ordenTrabajo.getTotal();
            abonado = ordenTrabajo.getAbonado();
            resta = total - abonado;

            asignarValoresDelPago(ordenTrabajo, total, abonado, resta);
        }

        boolean necesitaFactura = (idOrdenTrabajo != null) ? ordenTrabajo.isNecesitaFactura() : (request.getParameter("necesitaFactura") != null);
        boolean esCuentaCorriente = (idOrdenTrabajo != null) ? ordenTrabajo.isEsCuentaCorriente() : (request.getParameter("esCuentaCorriente") != null);

        Empleado empleado = (Empleado) request.getSession().getAttribute("empleadoLogueado");
        ordenTrabajo.setEmpleado(empleado);
        ordenTrabajo.setFechaPedido(LocalDate.now());
        ordenTrabajo.setEstadoOrden(estadoOrdenRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado Orden no encontrado")));
        ordenTrabajo.setNombreCliente(request.getParameter("nombreCliente"));
        ordenTrabajo.setTelefonoCliente(request.getParameter("telefonoCliente"));
        ordenTrabajo.setEsCuentaCorriente(esCuentaCorriente);
        ordenTrabajo.setNecesitaFactura(necesitaFactura);
        ordenTrabajo.setTipoProducto(request.getParameter("tipoProducto"));

        String fechaMuestraStr = request.getParameter("fechaMuestra");
        if (fechaMuestraStr != null && !fechaMuestraStr.isBlank()) {
            ordenTrabajo.setFechaMuestra(LocalDate.parse(fechaMuestraStr));
        }

        ordenTrabajo.setFechaEntrega(LocalDate.parse(request.getParameter("fechaEntrega")));
        ordenTrabajo.setHoraEntrega(request.getParameter("horaEntrega"));

        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    private void asignarValoresDelPago(OrdenTrabajo ordenTrabajo, int total, int abonado, int resta) {
        ordenTrabajo.setTotal(total);
        ordenTrabajo.setAbonado(abonado);
        ordenTrabajo.setResta(resta);

        asignarEstadoPago(ordenTrabajo, total, abonado, resta);
    }

    private void asignarEstadoPago(OrdenTrabajo ordenTrabajo, int total, int abonado, int resta) {
        if (total == abonado) {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        } else if (resta != 0) {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        } else {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        }
    }

    private void asignarMedioPago(OrdenTrabajo ordenTrabajo, String medioPagoDesdeRequest) {
        if (medioPagoDesdeRequest != null && !medioPagoDesdeRequest.isBlank()) {
            Long medioPagoId = Long.parseLong(medioPagoDesdeRequest);
            ordenTrabajo.setMedioPago(medioPagoRepository.findById(medioPagoId)
                    .orElseThrow(() -> new RuntimeException("Medio de pago no encontrado")));
        } else {
            throw new RuntimeException("Medio de pago es obligatorio");
        }
    }

    public List<OrdenTrabajo> buscarEstadoSinHacer(Empleado empleado, String tipoProducto) {
        Empleado desarrollador = empleadoService.traerEmpleadoPorUsername("benpm");
        Empleado community = empleadoService.traerEmpleadoPorUsername("maripm");

        List<OrdenTrabajo> ordenes = "todas".equals(tipoProducto)
                ? ordenTrabajoRepository.findByEstadoOrdenId(1L)
                : ordenTrabajoRepository.findByEstadoOrdenIdAndTipoProducto(1L, tipoProducto);

        return ordenes.stream()
                .filter(orden -> {
                    if (empleado.getId().equals(desarrollador.getId())) {
                        return orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else if (empleado.getId().equals(community.getId())) {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId())
                                && !orden.getEmpleado().getId().equals(community.getId());
                    }
                })
                .toList();
    }

    public List<OrdenTrabajo> buscarEstadoCorregir(Empleado empleado, String tipoProducto) {
        Empleado desarrollador = empleadoService.traerEmpleadoPorUsername("benpm");
        Empleado community = empleadoService.traerEmpleadoPorUsername("maripm");

        List<OrdenTrabajo> ordenes = "todas".equals(tipoProducto)
                ? ordenTrabajoRepository.findByEstadoOrdenId(4L)
                : ordenTrabajoRepository.findByEstadoOrdenIdAndTipoProducto(4L, tipoProducto);

        return ordenes.stream()
                .filter(orden -> {
                    if (empleado.getId().equals(desarrollador.getId())) {
                        return orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else if (empleado.getId().equals(community.getId())) {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId())
                                && !orden.getEmpleado().getId().equals(community.getId());
                    }
                })
                .toList();
    }

    public List<OrdenTrabajo> buscarEstadoEnProceso(Empleado empleado, String tipoProducto) {
        Empleado desarrollador = empleadoService.traerEmpleadoPorUsername("benpm");
        Empleado community = empleadoService.traerEmpleadoPorUsername("maripm");

        List<OrdenTrabajo> ordenes = "todas".equals(tipoProducto)
                ? ordenTrabajoRepository.findByEstadoOrdenId(2L)
                : ordenTrabajoRepository.findByEstadoOrdenIdAndTipoProducto(2L, tipoProducto);

        return ordenes.stream()
                .filter(orden -> {
                    if (empleado.getId().equals(desarrollador.getId())) {
                        return orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else if (empleado.getId().equals(community.getId())) {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId())
                                && !orden.getEmpleado().getId().equals(community.getId());
                    }
                })
                .toList();
    }

    public List<OrdenTrabajo> buscarEstadoListaParaRetirar(Empleado empleado, String tipoProducto){
        Empleado desarrollador = empleadoService.traerEmpleadoPorUsername("benpm");
        Empleado community = empleadoService.traerEmpleadoPorUsername("maripm");

        List<OrdenTrabajo> ordenes = "todas".equals(tipoProducto)
                ? ordenTrabajoRepository.findByEstadoOrdenId(3L)
                : ordenTrabajoRepository.findByEstadoOrdenIdAndTipoProducto(3L, tipoProducto);

        return ordenes.stream()
                .filter(orden -> {
                    if (empleado.getId().equals(desarrollador.getId())) {
                        return orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else if (empleado.getId().equals(community.getId())) {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId())
                                && !orden.getEmpleado().getId().equals(community.getId());
                    }
                })
                .toList();
    }

    public List<OrdenTrabajo> buscarTodas() {
        return ordenTrabajoRepository.findAll();
    }

    public void cambiarEstadoACorregir(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();
        EstadoOrden aCorregir = estadoOrdenRepository.findById(4L).get();
        ordenTrabajo.setEstadoOrden(aCorregir);
        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void cambiarEstadoAEnProceso(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();
        EstadoOrden enProceso = estadoOrdenRepository.findById(2L).get();
        ordenTrabajo.setEstadoOrden(enProceso);
        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void cambiarEstadoAListaParaRetirar(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();
        EstadoOrden listaParaRetirar = estadoOrdenRepository.findById(3L).get();
        ordenTrabajo.setEstadoOrden(listaParaRetirar);
        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void cambiarEstadoARetirada(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();
        EstadoOrden retirada = estadoOrdenRepository.findById(5L).get();
        ordenTrabajo.setEstadoOrden(retirada);
        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void cambiarEstadoASinHacer(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();
        EstadoOrden sinHacer = estadoOrdenRepository.findById(1L).get();
        ordenTrabajo.setEstadoOrden(sinHacer);
        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public void cambiarAAbonado(Long id) {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).get();

        if (ordenTrabajo.getResta() != 0) {
            int total = ordenTrabajo.getTotal();
            EstadoPago estadoAbonado = estadoPagoRepository.findById(3L).get();
            ordenTrabajo.setEstadoPago(estadoAbonado);
            ordenTrabajo.setResta(0);
            ordenTrabajo.setAbonado(total);
        }

        ordenTrabajoRepository.save(ordenTrabajo);
    }

    public List<OrdenTrabajo> buscarTodasConIDONombreOTelefono(String datoOrden, Empleado empleado) {
        Set<OrdenTrabajo> ordenesEncontradas = new HashSet<>();
        Empleado desarrollador = empleadoService.traerEmpleadoPorUsername("benpm");
        Empleado community = empleadoService.traerEmpleadoPorUsername("maripm");

        try {
            Long posibleId = Long.parseLong(datoOrden);
            ordenTrabajoRepository.findById(posibleId).ifPresent(ordenesEncontradas::add);
        } catch (NumberFormatException e) {
        }

        ordenesEncontradas.addAll(
            ordenTrabajoRepository.findByNombreClienteContainingIgnoreCaseOrTelefonoClienteContaining(datoOrden, datoOrden)
        );

        return ordenesEncontradas.stream()
                .filter(orden -> {
                    if (empleado.getId().equals(desarrollador.getId())) {
                        return orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else if (empleado.getId().equals(community.getId())) {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId());
                    } else {
                        return !orden.getEmpleado().getId().equals(desarrollador.getId())
                                && !orden.getEmpleado().getId().equals(community.getId());
                    }
                })
                .toList();
    }

    public void eliminar(Long id) {
        Assert.notNull(id, "El id no puede ser nulo");
        ordenTrabajoRepository.deleteById(id);
    }
}
