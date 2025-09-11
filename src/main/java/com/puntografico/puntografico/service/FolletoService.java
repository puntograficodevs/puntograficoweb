package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class FolletoService {

    private final FolletoRepository folletoRepository;

    private final TipoPapelFolletoRepository tipoPapelFolletoRepository;

    private final TipoColorFolletoRepository tipoColorFolletoRepository;

    private final TipoFazFolletoRepository tipoFazFolletoRepository;

    private final TamanioHojaFolletoRepository tamanioHojaFolletoRepository;

    private final TipoFolletoRepository tipoFolletoRepository;

    private final CantidadFolletoRepository cantidadFolletoRepository;

    public Folleto crear(HttpServletRequest request) {
        String tipoPapelFolletoString = request.getParameter("tipoPapelFolleto.id");
        String tipoColorFolletoString = request.getParameter("tipoColorFolleto.id");
        String tipoFazFolletoString = request.getParameter("tipoFazFolleto.id");
        String tamanioHojaFolletoString = request.getParameter("tamanioHojaFolleto.id");
        String tipoFolletoString = request.getParameter("tipoFolleto.id");
        String cantidadFolletoString = request.getParameter("cantidadFolleto.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoPapelFolletoString, "tipoPapelFolletoString no puede venir vacío.");
        Assert.notNull(tipoColorFolletoString, "tipoColorFolletoString no puede venir vacío.");
        Assert.notNull(tipoFazFolletoString, "tipoFazFolletoString no puede venir vacío.");
        Assert.notNull(tamanioHojaFolletoString, "tamanioHojaFolletoString no puede venir vacío.");
        Assert.notNull(tipoFolletoString, "tipoFolletoString no puede venir vacío.");
        Assert.notNull(cantidadFolletoString, "cantidadFolletoString no puede venir vacío.");

        TipoPapelFolleto tipoPapelFolleto = tipoPapelFolletoRepository.findById(Long.parseLong(tipoPapelFolletoString)).get();
        TipoColorFolleto tipoColorFolleto = tipoColorFolletoRepository.findById(Long.parseLong(tipoColorFolletoString)).get();
        TipoFazFolleto tipoFazFolleto = tipoFazFolletoRepository.findById(Long.parseLong(tipoFazFolletoString)).get();
        TamanioHojaFolleto tamanioHojaFolleto = tamanioHojaFolletoRepository.findById(Long.parseLong(tamanioHojaFolletoString)).get();
        TipoFolleto tipoFolleto = tipoFolletoRepository.findById(Long.parseLong(tipoFolletoString)).get();
        CantidadFolleto cantidadFolleto = cantidadFolletoRepository.findById(Long.parseLong(cantidadFolletoString)).get();

        if (cantidadString.isEmpty() || cantidadString.isBlank()) {
            cantidadString = cantidadFolleto.getCantidad();
        }

        Folleto folleto = new Folleto();
        folleto.setConPlegado(request.getParameter("conPlegado") != null);
        folleto.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        folleto.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        folleto.setInformacionAdicional(request.getParameter("informacionAdicional"));
        folleto.setTipoPapelFolleto(tipoPapelFolleto);
        folleto.setTipoColorFolleto(tipoColorFolleto);
        folleto.setTipoFazFolleto(tipoFazFolleto);
        folleto.setTamanioHojaFolleto(tamanioHojaFolleto);
        folleto.setTipoFolleto(tipoFolleto);
        folleto.setCantidadFolleto(cantidadFolleto);
        folleto.setCantidad(Integer.parseInt(cantidadString));

        return folletoRepository.save(folleto);
    }
}
