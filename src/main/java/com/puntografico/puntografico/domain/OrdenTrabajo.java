package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.EstadoOrden;
import com.puntografico.puntografico.enums.EstadoPago;
import com.puntografico.puntografico.enums.MedioPago;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orden_trabajo")
@Getter @Setter
public class OrdenTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNombre;

    private String clienteTelefono;

    private boolean esCuentaCorriente;

    private LocalDate fechaPedido;

    private LocalDate fechaMuestra;

    private LocalDate fechaEntrega;

    private String horaEntrega;

    @Enumerated(EnumType.STRING)
    private MedioPago medioPago;

    @Enumerated(EnumType.STRING)
    private EstadoPago estadoPago;

    @Enumerated(EnumType.STRING)
    private EstadoOrden estadoOrden;

    private int cantidad;

    private double total;

    private double abonado;

    private double resta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaProducto categoriaProducto;
}
