package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.MedidaEstandarCierraBolsas;
import com.puntografico.puntografico.enums.TipoTroquelado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cierra_bolsas")
@Getter @Setter
public class CierraBolsas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean esTroquelado;

    @Enumerated(EnumType.STRING)
    private TipoTroquelado tipoTroquelado;

    @Enumerated(EnumType.STRING)
    private MedidaEstandarCierraBolsas medidaEstandarCierraBolsas;

    private String medidaPersonalizada;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
