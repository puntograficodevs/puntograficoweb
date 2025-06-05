package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.MedidaEstandarSobre;
import com.puntografico.puntografico.enums.TipoColor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sobre")
@Getter @Setter
public class Sobre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MedidaEstandarSobre medidaEstandarSobre;

    private String medidaPersonalizada;

    @Enumerated(EnumType.STRING)
    private TipoColor tipoColor;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
