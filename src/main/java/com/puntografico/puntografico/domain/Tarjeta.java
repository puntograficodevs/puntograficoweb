package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tarjeta")
@Getter @Setter
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPapelTarjeta tipoPapelTarjeta;

    @Enumerated(EnumType.STRING)
    private TipoColor tipoColor;

    @Enumerated(EnumType.STRING)
    private TipoFaz tipoFaz;

    @Enumerated(EnumType.STRING)
    private TipoLaminado tipoLaminado;

    @Enumerated(EnumType.STRING)
    private MedidaEstandarTarjeta medidaEstandarTarjeta;

    private String medidaPersonalizada;

    @Enumerated(EnumType.STRING)
    private CantidadEstandarTYF cantidadEstandarTYF;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
