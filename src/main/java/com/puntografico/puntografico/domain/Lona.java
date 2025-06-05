package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.MedidaEstandarLonaComun;
import com.puntografico.puntografico.enums.TipoLona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lona")
@Getter @Setter
public class Lona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MedidaEstandarLonaComun medidaEstandarLonaComun;

    private String medidaPersonalizada;

    private boolean conOjales;

    private boolean conOjalesConRefuerzo;

    private boolean conBolsillos;

    private boolean conDemasiaParaTensado;

    private boolean conSolapado;

    @Enumerated(EnumType.STRING)
    private TipoLona tipoLona;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
