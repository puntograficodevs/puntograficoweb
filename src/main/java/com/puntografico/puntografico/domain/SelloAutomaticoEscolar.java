package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.ModeloSelloAutomaticoEscolar;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sello_automatico_escolar")
@Getter @Setter
public class SelloAutomaticoEscolar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModeloSelloAutomaticoEscolar modeloSelloAutomaticoEscolar;

    private boolean esVertical;

    private boolean esHorizontal;

    private String textoLineaUno;

    private String textoLineaDos;

    private String textoLineaTres;

    private String textoLineaCuatro;

    private String tipografia;

    private String dibujo;

    private String archivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
