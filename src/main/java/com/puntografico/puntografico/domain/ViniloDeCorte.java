package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TraeMaterialVinilo;

import javax.persistence.*;

@Entity
@Table(name = "vinilo_de_corte")
public class ViniloDeCorte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean esPromocional;

    private boolean esOracal;

    private String codigoColor;

    private boolean conColocacion;

    @Enumerated(EnumType.STRING)
    private TraeMaterialVinilo traeMaterialVinilo;

    private String medida;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
