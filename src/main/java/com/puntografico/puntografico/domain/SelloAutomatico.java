package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.ModeloSelloAutomatico;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sello_automatico")
@Getter @Setter
public class SelloAutomatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean esProfesional;

    private boolean esParticular;

    @Enumerated(EnumType.STRING)
    private ModeloSelloAutomatico modeloSelloAutomatico;

    private String textoLineaUno;

    private String textoLineaDos;

    private String textoLineaTres;

    private String textoLineaCuatro;

    private String tipografiaLineaUno;

    private String archivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
