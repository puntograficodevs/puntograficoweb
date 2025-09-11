package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class ImpresionService {

    private final ImpresionRepository impresionRepository;

    private final TipoColorImpresionRepository tipoColorImpresionRepository;

    private final TamanioHojaImpresionRepository tamanioHojaImpresionRepository;

    private final TipoFazImpresionRepository tipoFazImpresionRepository;

    private final TipoPapelImpresionRepository tipoPapelImpresionRepository;

    private final CantidadImpresionRepository cantidadImpresionRepository;

    private final TipoImpresionRepository tipoImpresionRepository;

    public Impresion crear(HttpServletRequest request) {
        String tipoColorImpresionString = request.getParameter("tipoColorImpresion.id");
        String tamanioHojaImpresionString = request.getParameter("tamanioHojaImpresion.id");
        String tipoFazImpresionString = request.getParameter("tipoFazImpresion.id");
        String tipoPapelImpresionString = request.getParameter("tipoPapelImpresion.id");
        String cantidadImpresionString = request.getParameter("cantidadImpresion.id");
        String tipoImpresionString = request.getParameter("tipoImpresion.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoColorImpresionString, "tipoColorImpresionString es un campo obligatorio.");
        Assert.notNull(tamanioHojaImpresionString, "tamanioHojaImpresionString es un campo obligatorio.");
        Assert.notNull(tipoFazImpresionString, "tipoFazImpresionString es un campo obligatorio.");
        Assert.notNull(tipoPapelImpresionString, "tipoPapelImpresionString es un campo obligatorio.");
        Assert.notNull(tipoImpresionString, "tipoImpresionString es un campo obligatorio.");
        Assert.notNull(cantidadString, "cantidadString es un campo obligatorio.");
        Assert.notNull(cantidadImpresionString, "cantidadImpresionString es un campo obligatorio.");

        int cantidad = Integer.parseInt(cantidadString);
        TipoColorImpresion tipoColorImpresion = tipoColorImpresionRepository.findById(Long.parseLong(tipoColorImpresionString)).get();
        TamanioHojaImpresion tamanioHojaImpresion = tamanioHojaImpresionRepository.findById(Long.parseLong(tamanioHojaImpresionString)).get();
        TipoFazImpresion tipoFazImpresion = tipoFazImpresionRepository.findById(Long.parseLong(tipoFazImpresionString)).get();
        TipoPapelImpresion tipoPapelImpresion = tipoPapelImpresionRepository.findById(Long.parseLong(tipoPapelImpresionString)).get();
        TipoImpresion tipoImpresion = tipoImpresionRepository.findById(Long.parseLong(tipoImpresionString)).get();
        CantidadImpresion cantidadImpresion = cantidadImpresionRepository.findById(Long.parseLong(cantidadImpresionString)).get();

        Impresion impresion = new Impresion();
        impresion.setEsAnillado(request.getParameter("esAnillado") != null);
        impresion.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        impresion.setInformacionAdicional(request.getParameter("informacionAdicional"));
        impresion.setTipoColorImpresion(tipoColorImpresion);
        impresion.setTamanioHojaImpresion(tamanioHojaImpresion);
        impresion.setTipoFazImpresion(tipoFazImpresion);
        impresion.setTipoPapelImpresion(tipoPapelImpresion);
        impresion.setCantidadImpresion(cantidadImpresion);
        impresion.setTipoImpresion(tipoImpresion);
        impresion.setCantidad(cantidad);

        return impresionRepository.save(impresion);
    }
}
