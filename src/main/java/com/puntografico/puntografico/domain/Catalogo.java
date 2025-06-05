package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TipoFaz;
import com.puntografico.puntografico.enums.TipoLaminado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "catalogo")
@Getter @Setter
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoLaminado tipoLaminado;

    private String tipoPapelHojas;

    @Enumerated(EnumType.STRING)
    private TipoFaz tipoFaz;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
