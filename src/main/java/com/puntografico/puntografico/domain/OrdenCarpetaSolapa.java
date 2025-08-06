package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "orden_carpeta_solapa")
@Getter
@Setter
public class OrdenCarpetaSolapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carpeta_solapa")
    private CarpetaSolapa carpetaSolapa;
}
