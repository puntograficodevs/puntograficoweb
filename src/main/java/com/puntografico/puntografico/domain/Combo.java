package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.CantidadesComboEmprendedor;
import com.puntografico.puntografico.enums.TipoCombo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "combo")
@Getter @Setter
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCombo tipoCombo;

    private String descripcionCombo;

    @Enumerated(EnumType.STRING)
    private CantidadesComboEmprendedor cantidadesComboEmprendedor;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
