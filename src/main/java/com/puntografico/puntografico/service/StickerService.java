package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSticker;
import com.puntografico.puntografico.domain.MedidaSticker;
import com.puntografico.puntografico.domain.Sticker;
import com.puntografico.puntografico.domain.TipoTroqueladoSticker;
import com.puntografico.puntografico.repository.CantidadStickerRepository;
import com.puntografico.puntografico.repository.MedidaStickerRepository;
import com.puntografico.puntografico.repository.StickerRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoStickerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class StickerService {

    private final StickerRepository stickerRepository;

    private final TipoTroqueladoStickerRepository tipoTroqueladoStickerRepository;

    private final CantidadStickerRepository cantidadStickerRepository;

    private final MedidaStickerRepository medidaStickerRepository;

    public Sticker crear(HttpServletRequest request) {
        String tipoTroqueladoStickerString = request.getParameter("tipoTroqueladoSticker.id");
        String cantidadStickerString = request.getParameter("cantidadSticker.id");
        String medidaStickerString = request.getParameter("medidaSticker.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaStickerString, "medidaStickerString es un dato obligatorio.");
        Assert.notNull(tipoTroqueladoStickerString, "tipoTroqueladoStickerString es un dato obligatorio.");
        Assert.notNull(cantidadStickerString, "cantidadStickerString es un dato obligatorio.");

        TipoTroqueladoSticker tipoTroqueladoSticker = tipoTroqueladoStickerRepository.findById(Long.parseLong(tipoTroqueladoStickerString)).get();
        CantidadSticker cantidadSticker = cantidadStickerRepository.findById(Long.parseLong(cantidadStickerString)).get();
        MedidaSticker medidaSticker = medidaStickerRepository.findById(Long.parseLong(medidaStickerString)).get();

        int cantidad;

        if (cantidadSticker.getId() != 4) {
            cantidad = Integer.parseInt(cantidadSticker.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Sticker sticker = new Sticker();
        sticker.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        sticker.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        sticker.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        sticker.setInformacionAdicional(request.getParameter("informacionAdicional"));
        sticker.setTipoTroqueladoSticker(tipoTroqueladoSticker);
        sticker.setCantidadSticker(cantidadSticker);
        sticker.setMedidaSticker(medidaSticker);
        sticker.setCantidad(cantidad);

        return stickerRepository.save(sticker);
    }
}
