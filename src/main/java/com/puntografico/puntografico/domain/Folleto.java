package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "folleto")
@Getter @Setter
public class Folleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoPapelFolleto tipoPapelFolleto;

    @Enumerated(EnumType.STRING)
    private TipoColor tipoColor;

    @Enumerated(EnumType.STRING)
    private TipoFaz tipoFaz;

    @Enumerated(EnumType.STRING)
    private TamanioHojaFolleto tamanioHojaFolleto;

    @Enumerated(EnumType.STRING)
    private TipoFolleto tipoFolleto;

    @Enumerated(EnumType.STRING)
    private CantidadEstandarTYF cantidadEstandarTYF;

    private boolean conPlegado;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
