package com.puntografico.puntografico.domain;

import javax.persistence.*;

@Entity
@Table(name = "vinilo_plastico_corrugado")
public class ViniloPlasticoCorrugado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medida;

    private boolean conOjales;

    private int cantidadOjales;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
