package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CarpetaSolapa;
import com.puntografico.puntografico.domain.TipoFazCarpetaSolapa;
import com.puntografico.puntografico.domain.TipoLaminadoCarpetaSolapa;
import com.puntografico.puntografico.repository.CarpetaSolapaRepository;
import com.puntografico.puntografico.repository.TipoFazCarpetaSolapaRepository;
import com.puntografico.puntografico.repository.TipoLaminadoCarpetaSolapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class CarpetaSolapaService {

    @Autowired
    private CarpetaSolapaRepository carpetaSolapaRepository;

    @Autowired
    private TipoFazCarpetaSolapaRepository tipoFazCarpetaSolapaRepository;

    @Autowired
    private TipoLaminadoCarpetaSolapaRepository tipoLaminadoCarpetaSolapaRepository;

    public CarpetaSolapa crear(HttpServletRequest request) {
        String tipoPapel = request.getParameter("tipoPapel");
        String cantidad = request.getParameter("cantidad");
        String tipoLaminado = request.getParameter("tipoLaminadoCarpetaSolapa.id");
        TipoLaminadoCarpetaSolapa tipoLaminadoCarpetaSolapa = tipoLaminadoCarpetaSolapaRepository.findById(Long.parseLong(tipoLaminado)).get();
        String tipoFaz = request.getParameter("tipoFazCarpetaSolapa.id");
        TipoFazCarpetaSolapa tipoFazCarpetaSolapa = tipoFazCarpetaSolapaRepository.findById(Long.parseLong(tipoFaz)).get();

        Assert.notNull(tipoPapel, "El tipo de papel es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");
        Assert.notNull(tipoLaminado, "El tipo de laminado es un dato obligatorio.");
        Assert.notNull(tipoFaz, "El tipo de faz es un dato obligatorio.");

        CarpetaSolapa carpetaSolapa = new CarpetaSolapa();
        carpetaSolapa.setTipoPapel(tipoPapel);
        carpetaSolapa.setCantidad(Integer.parseInt(cantidad));
        carpetaSolapa.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        carpetaSolapa.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        carpetaSolapa.setInformacionAdicional(request.getParameter("informacionAdicional"));
        carpetaSolapa.setTipoLaminadoCarpetaSolapa(tipoLaminadoCarpetaSolapa);
        carpetaSolapa.setTipoFazCarpetaSolapa(tipoFazCarpetaSolapa);

        return carpetaSolapaRepository.save(carpetaSolapa);
    }
}
