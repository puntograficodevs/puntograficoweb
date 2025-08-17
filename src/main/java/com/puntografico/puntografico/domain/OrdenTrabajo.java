package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orden_trabajo")
@Getter
@Setter
public class OrdenTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String telefonoCliente;

    @Column(nullable = false)
    private boolean esCuentaCorriente;

    @Column(nullable = false)
    private LocalDate fechaPedido;

    private LocalDate fechaMuestra;

    @Column(nullable = false)
    private LocalDate fechaEntrega;

    private String horaEntrega;

    @Column(nullable = false)
    private boolean necesitaFactura;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private int abonado;

    @Column(nullable = false)
    private int resta;

    @Column(nullable = false)
    private String tipoProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medio_pago")
    private MedioPago medioPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_pago")
    private EstadoPago estadoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_orden")
    private EstadoOrden estadoOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
