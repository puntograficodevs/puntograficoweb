package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.AdicionalVinilo;
import com.puntografico.puntografico.enums.TipoCorte;
import com.puntografico.puntografico.enums.TipoVinilo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vinilo")
@Getter @Setter
public class Vinilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medida;

    @Enumerated(EnumType.STRING)
    private TipoVinilo tipoVinilo;

    @Enumerated(EnumType.STRING)
    private AdicionalVinilo adicionalVinilo;

    @Enumerated(EnumType.STRING)
    private TipoCorte tipoCorte;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
