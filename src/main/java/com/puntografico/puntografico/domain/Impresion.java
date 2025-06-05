package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TamanioHoja;
import com.puntografico.puntografico.enums.TipoColor;
import com.puntografico.puntografico.enums.TipoFaz;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "impresion")
@Getter @Setter
public class Impresion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoColor tipoColor;

    @Enumerated(EnumType.STRING)
    private TamanioHoja tamanioHoja;

    @Enumerated(EnumType.STRING)
    private TipoFaz tipoFaz;

    private boolean esAnillado;

    private String tipoPapel;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
