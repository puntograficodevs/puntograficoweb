package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cuaderno_anillado")
@Getter @Setter
public class CuadernoAnillado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medida;

    private String tipoTapa;

    private int cantidadHojas;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
