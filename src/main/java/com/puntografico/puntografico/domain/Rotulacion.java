package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TipoCorte;
import com.puntografico.puntografico.enums.TipoRotulacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rotulacion")
@Getter @Setter
public class Rotulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRotulacion tipoRotulacion;

    private boolean esLaminado;

    @Enumerated(EnumType.STRING)
    private TipoCorte tipoCorte;

    private String horarioRotulacion;

    private String direccionRotulacion;

    private String medida;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
