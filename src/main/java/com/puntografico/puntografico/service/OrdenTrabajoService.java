package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Empleado;
import com.puntografico.puntografico.domain.EstadoOrden;
import com.puntografico.puntografico.domain.EstadoPago;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.EstadoOrdenRepository;
import com.puntografico.puntografico.repository.EstadoPagoRepository;
import com.puntografico.puntografico.repository.MedioPagoRepository;
import com.puntografico.puntografico.repository.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    private EstadoOrdenRepository estadoOrdenRepository;

    @Autowired
    private EstadoPagoRepository estadoPagoRepository;

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    public OrdenTrabajo crear(HttpServletRequest request) {
        OrdenTrabajo ordenTrabajo = new OrdenTrabajo();

        Empleado empleado = (Empleado) request.getSession().getAttribute("empleadoLogueado");
        System.out.println("EL EMPLEADOE ES " + empleado.getNombre());
        ordenTrabajo.setEmpleado((Empleado) request.getSession().getAttribute("empleadoLogueado"));
        ordenTrabajo.setFechaPedido(LocalDate.now());
        ordenTrabajo.setEstadoOrden(estadoOrdenRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Estado Orden no encontrado")));
        ordenTrabajo.setNombreCliente(request.getParameter("nombreCliente"));
        ordenTrabajo.setTelefonoCliente(request.getParameter("telefonoCliente"));
        ordenTrabajo.setEsCuentaCorriente(request.getParameter("esCuentaCorriente") != null);

        String fechaMuestraStr = request.getParameter("fechaMuestra");
        if (fechaMuestraStr != null && !fechaMuestraStr.isBlank()) {
            ordenTrabajo.setFechaMuestra(LocalDate.parse(fechaMuestraStr));
        }

        ordenTrabajo.setFechaEntrega(LocalDate.parse(request.getParameter("fechaEntrega")));
        ordenTrabajo.setHoraEntrega(request.getParameter("horaEntrega"));
        ordenTrabajo.setNecesitaFactura(request.getParameter("necesitaFactura") != null);
        ordenTrabajo.setTotal(Integer.parseInt(request.getParameter("total")));
        ordenTrabajo.setAbonado(Integer.parseInt(request.getParameter("abonado")));
        ordenTrabajo.setTipoProducto(request.getParameter("tipoProducto"));

        // Calculamos resta (por si no viene en el form o es error)
        int resta = ordenTrabajo.getTotal() - ordenTrabajo.getAbonado();
        ordenTrabajo.setResta(resta);

        String medioPagoIdStr = request.getParameter("medioPago.id");
        if (medioPagoIdStr != null && !medioPagoIdStr.isBlank()) {
            Long medioPagoId = Long.parseLong(medioPagoIdStr);
            ordenTrabajo.setMedioPago(medioPagoRepository.findById(medioPagoId)
                    .orElseThrow(() -> new RuntimeException("Medio de pago no encontrado")));
        } else {
            throw new RuntimeException("Medio de pago es obligatorio");
        }

        // Asignar estadoPago según la lógica
        if (ordenTrabajo.getTotal() == ordenTrabajo.getAbonado()) {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(3L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        } else if (ordenTrabajo.getResta() != 0) {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(2L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        } else {
            ordenTrabajo.setEstadoPago(estadoPagoRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("Estado Pago no encontrado")));
        }

        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    public List<OrdenTrabajo> buscarEstadoSinHacer(){
        return ordenTrabajoRepository.findByEstadoOrdenId(1L);
    };

    public List<OrdenTrabajo> buscarEstadoCorregir(){
        return ordenTrabajoRepository.findByEstadoOrdenId(4L);
    };

    public List<OrdenTrabajo> buscarEstadoEnProceso(){
        return ordenTrabajoRepository.findByEstadoOrdenId(2L);
    };

    public List<OrdenTrabajo> buscarEstadoListaParaRetirar(){
        return ordenTrabajoRepository.findByEstadoOrdenId(3L);
    };

    public List<OrdenTrabajo> buscarTodas() {
        return ordenTrabajoRepository.findAll();
    }
}
