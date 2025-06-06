package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item_configurable_opcion")
@Getter @Setter
public class ItemConfigurableOpcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String valor;

    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_configurable")
    private ItemConfigurable itemConfigurable;
}
