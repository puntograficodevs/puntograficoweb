package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "talonario")
@Getter @Setter
public class Talonario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoTalonario tipoTalonario;

    private TipoTroqueladoTalonario tipoTroqueladoTalonario;

    private boolean conNumerado;

    private String detalleNumerado;

    private ModoTalonario modoTalonario;

    private boolean esEncolado;

    private TipoColor tipoColor;

    private MedidaTalonario medidaTalonario;

    private String medidaPersonalizada;

    private TipoPapelTalonario tipoPapelTalonario;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
