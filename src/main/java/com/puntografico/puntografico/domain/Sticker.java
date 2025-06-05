package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TipoTroquelado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sticker")
@Getter @Setter
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medida;

    private boolean esTroquelado;

    @Enumerated(EnumType.STRING)
    private TipoTroquelado tipoTroquelado;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
