package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.EstadoOrden;
import com.puntografico.puntografico.domain.EstadoPago;
import com.puntografico.puntografico.domain.OrdenTrabajo;
import com.puntografico.puntografico.repository.EstadoOrdenRepository;
import com.puntografico.puntografico.repository.EstadoPagoRepository;
import com.puntografico.puntografico.repository.OrdenTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;

    @Autowired
    private EstadoOrdenRepository estadoOrdenRepository;

    @Autowired
    private EstadoPagoRepository estadoPagoRepository;

    public OrdenTrabajo crear(OrdenTrabajo ordenTrabajo) {
        Assert.notNull(ordenTrabajo.getNombreCliente(), "Es necesario conocer el nombre del cliente.");
        Assert.notNull(ordenTrabajo.getTelefonoCliente(), "Es necesario tener el contacto del cliente.");
        Assert.notNull(ordenTrabajo.getFechaEntrega(), "Debe asignarse una fecha de entrega.");

        ordenTrabajo.setFechaPedido(LocalDate.now());
        EstadoOrden estadoOrden = estadoOrdenRepository.findById(1L).get();
        EstadoPago estadoPago;
        if (ordenTrabajo.getTotal() == ordenTrabajo.getAbonado()) {
            estadoPago = estadoPagoRepository.findById(3L).get();
        } else if (ordenTrabajo.getAbonado() == 0) {
            estadoPago = estadoPagoRepository.findById(1L).get();
        } else {
            estadoPago = estadoPagoRepository.findById(2L).get();
        }
        ordenTrabajo.setEstadoPago(estadoPago);
        ordenTrabajo.setEstadoOrden(estadoOrden);

        return ordenTrabajoRepository.save(ordenTrabajo);
    }
}
