package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.OrdenStickerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class OrdenStickerService {

    private final OrdenStickerRepository ordenStickerRepository;

    public OrdenSticker crear(OrdenTrabajo ordenTrabajo, Sticker sticker) {
        Assert.notNull(ordenTrabajo, "Debe venir una orden de trabajo para enlazar.");
        Assert.notNull(sticker, "Debe venir un sticker para enlazar.");

        OrdenSticker ordenSticker = new OrdenSticker();
        ordenSticker.setCantidad(sticker.getCantidad());
        ordenSticker.setOrdenTrabajo(ordenTrabajo);
        ordenSticker.setSticker(sticker);

        return ordenStickerRepository.save(ordenSticker);
    }

    public OrdenSticker buscarPorId(Long id) {
        return ordenStickerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrdenSticker con ID " + id + " no encontrada"));
    }

    public OrdenSticker buscarPorOrdenId(Long id) {
        return ordenStickerRepository.findByOrdenTrabajo_Id(id);
    }
}
