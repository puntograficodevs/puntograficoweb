package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Catalogo;
import com.puntografico.puntografico.domain.TipoFazCatalogo;
import com.puntografico.puntografico.domain.TipoLaminadoCatalogo;
import com.puntografico.puntografico.repository.CatalogoRepository;
import com.puntografico.puntografico.repository.TipoFazCatalogoRepository;
import com.puntografico.puntografico.repository.TipoLaminadoCatalogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class CatalogoService {

    private final CatalogoRepository catalogoRepository;

    private final TipoLaminadoCatalogoRepository tipoLaminadoCatalogoRepository;

    private final TipoFazCatalogoRepository tipoFazCatalogoRepository;

    public Catalogo crear(HttpServletRequest request) {
        String tipoPapel = request.getParameter("tipoPapel");
        String tipoLaminado = request.getParameter("tipoLaminadoCatalogo.id");
        String tipoFaz = request.getParameter("tipoFazCatalogo.id");
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(tipoPapel, "El tipo de papel es un dato obligatorio.");
        Assert.notNull(tipoLaminado, "El tipo de laminado es un dato obligatorio.");
        Assert.notNull(tipoFaz, "El tipo de faz es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");

        Catalogo catalogo = new Catalogo();
        TipoFazCatalogo tipoFazCatalogo = tipoFazCatalogoRepository.findById(Long.parseLong(tipoFaz)).get();
        TipoLaminadoCatalogo tipoLaminadoCatalogo = tipoLaminadoCatalogoRepository.findById(Long.parseLong(tipoLaminado)).get();

        catalogo.setTipoPapel(tipoPapel);
        catalogo.setTipoFazCatalogo(tipoFazCatalogo);
        catalogo.setTipoLaminadoCatalogo(tipoLaminadoCatalogo);
        catalogo.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        catalogo.setInformacionAdicional(request.getParameter("informacionAdicional"));
        catalogo.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        catalogo.setCantidad(Integer.parseInt(cantidad));

        return catalogoRepository.save(catalogo);
    }
}
