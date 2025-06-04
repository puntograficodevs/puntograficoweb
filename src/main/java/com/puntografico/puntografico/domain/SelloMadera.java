package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.TamanioSelloMaderna;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sello_madera")
@Getter @Setter
public class SelloMadera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TamanioSelloMaderna tamanioSelloMaderna;

    private String tamanioPersonalizado;

    private boolean adicionalPerilla;

    private String textoLineaUno;

    private String textoLineaDos;

    private String textoLineaTres;

    private String textoLineaCuatro;

    private String tipografiaLineaUno;

    private String archivo;

    private boolean adicionalDisenio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
