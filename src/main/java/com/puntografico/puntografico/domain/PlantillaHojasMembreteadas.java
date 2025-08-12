package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "plantilla_hojas_membreteadas")
@Getter
@Setter
public class PlantillaHojasMembreteadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int precio;

    @Column(nullable = false)
    private int cantidad_hojas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medida_hojas_membreteadas")
    private MedidaHojasMembreteadas medidaHojasMembreteadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_color_hojas_membreteadas")
    private TipoColorHojasMembreteadas tipoColorHojasMembreteadas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cantidad_hojas_membreteadas")
    private CantidadHojasMembreteadas cantidadHojasMembreteadas;
}
