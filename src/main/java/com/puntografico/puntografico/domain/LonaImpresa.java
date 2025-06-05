package com.puntografico.puntografico.domain;

import com.puntografico.puntografico.enums.MedidaEstandarLonaImpresa;
import com.puntografico.puntografico.enums.TipoLona;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "lona_impresa")
@Getter @Setter
public class LonaImpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MedidaEstandarLonaImpresa medidaEstandarLonaImpresa;

    private boolean conAdicionalPortabanner;

    @Enumerated(EnumType.STRING)
    private TipoLona tipoLona;

    private boolean adicionalDisenio;

    private String archivo;

    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orden_trabajo")
    private OrdenTrabajo ordenTrabajo;
}
