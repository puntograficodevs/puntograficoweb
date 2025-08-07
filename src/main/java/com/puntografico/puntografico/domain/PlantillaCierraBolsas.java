package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.SQLQuery;

import javax.persistence.*;

@Entity
@Table(name = "plantilla_cierra_bolsas")
@Getter @Setter
public class PlantillaCierraBolsas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_troquelado_cierra_bolsas")
    private TipoTroqueladoCierraBolsas tipoTroqueladoCierraBolsas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medida_cierra_bolsas")
    private MedidaCierraBolsas medidaCierraBolsas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cantidad_cierra_bolsas")
    private CantidadCierraBolsas cantidadCierraBolsas;
}
