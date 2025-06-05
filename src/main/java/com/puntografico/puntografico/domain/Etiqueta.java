package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TamanioPerforacion;
import com.puntografico.puntografico.enums.TipoFaz;
import com.puntografico.puntografico.enums.TipoLaminado;
import com.puntografico.puntografico.enums.TipoPapelEtiqueta;
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

    private String medida;

    @Enumerated(EnumType.STRING)
    private TipoPapelEtiqueta tipoPapelEtiqueta;

    @Enumerated(EnumType.STRING)
    private TipoLaminado tipoLaminado;

    private boolean conPerforacion;

    @Enumerated(EnumType.STRING)
    private TamanioPerforacion tamanioPerforacion;

    @Enumerated(EnumType.STRING)
    private TipoFaz tipoFaz;

    private boolean conPerforacionAdicional;

    private boolean conMarcaAdicional;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
