package com.puntografico.puntografico.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "etiqueta")
@Getter @Setter
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medidaPersonalizada;

    private boolean conPerforacionAdicional;

    private boolean conMarcaAdicional;

    private String enlaceArchivo;

    private boolean conAdicionalDisenio;

    private String informacionAdicional;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_papel_etiqueta")
    private TipoPapelEtiqueta tipoPapelEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_laminado_etiqueta")
    private TipoLaminadoEtiqueta tipoLaminadoEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tamanio_perforacion")
    private TamanioPerforacion tamanioPerforacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_faz_etiqueta")
    private TipoFazEtiqueta tipoFazEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cantidad_etiqueta")
    private CantidadEtiqueta cantidadEtiqueta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medida_etiqueta")
    private MedidaEtiqueta medidaEtiqueta;
}
