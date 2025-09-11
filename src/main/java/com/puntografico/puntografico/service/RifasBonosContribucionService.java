package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.RifasBonosContribucion;
import com.puntografico.puntografico.domain.TipoColorRifa;
import com.puntografico.puntografico.domain.TipoPapelRifa;
import com.puntografico.puntografico.domain.TipoTroqueladoRifa;
import com.puntografico.puntografico.repository.RifasBonosContribucionRepository;
import com.puntografico.puntografico.repository.TipoColorRifaRepository;
import com.puntografico.puntografico.repository.TipoPapelRifaRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoRifaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class RifasBonosContribucionService {

    private final RifasBonosContribucionRepository rifasBonosContribucionRepository;

    private final TipoPapelRifaRepository tipoPapelRifaRepository;

    private final TipoColorRifaRepository tipoColorRifaRepository;

    private final TipoTroqueladoRifaRepository tipoTroqueladoRifaRepository;

    public RifasBonosContribucion crear(HttpServletRequest request) {
        String medida = request.getParameter("medida");
        String cantidadString = request.getParameter("cantidad");
        String tipoPapelRifaString = request.getParameter("tipoPapelRifa.id");
        String tipoColorRifaString = request.getParameter("tipoColorRifa.id");
        String tipoTroqueladoRifaString = request.getParameter("tipoTroqueladoRifa.id");

        Assert.notNull(medida, "La medida es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");
        Assert.notNull(tipoPapelRifaString, "El tipo de papel es un dato obligatorio.");
        Assert.notNull(tipoTroqueladoRifaString, "El tipo de troquelado es un dato obligatorio.");
        Assert.notNull(tipoColorRifaString, "El tipo de color es un dato obligatorio.");

        TipoPapelRifa tipoPapelRifa = tipoPapelRifaRepository.findById(Long.parseLong(tipoPapelRifaString)).get();
        TipoTroqueladoRifa tipoTroqueladoRifa = tipoTroqueladoRifaRepository.findById(Long.parseLong(tipoTroqueladoRifaString)).get();
        TipoColorRifa tipoColorRifa = tipoColorRifaRepository.findById(Long.parseLong(tipoColorRifaString)).get();

        RifasBonosContribucion rifasBonosContribucion = new RifasBonosContribucion();
        rifasBonosContribucion.setConNumerado(request.getParameter("conNumerado") != null);
        rifasBonosContribucion.setDetalleNumerado(request.getParameter("detalleNumerado"));
        rifasBonosContribucion.setConEncolado(request.getParameter("conEncolado") != null);
        rifasBonosContribucion.setMedida(medida);
        rifasBonosContribucion.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        rifasBonosContribucion.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        rifasBonosContribucion.setInformacionAdicional(request.getParameter("informacionAdicional"));
        rifasBonosContribucion.setCantidad(Integer.parseInt(cantidadString));
        rifasBonosContribucion.setTipoPapelRifa(tipoPapelRifa);
        rifasBonosContribucion.setTipoTroqueladoRifa(tipoTroqueladoRifa);
        rifasBonosContribucion.setTipoColorRifa(tipoColorRifa);

        return rifasBonosContribucionRepository.save(rifasBonosContribucion);
    }
}
