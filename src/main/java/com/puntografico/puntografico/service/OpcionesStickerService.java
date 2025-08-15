package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSticker;
import com.puntografico.puntografico.domain.MedidaSticker;
import com.puntografico.puntografico.domain.TipoTroqueladoSticker;
import com.puntografico.puntografico.repository.CantidadStickerRepository;
import com.puntografico.puntografico.repository.MedidaStickerRepository;
import com.puntografico.puntografico.repository.TipoTroqueladoStickerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class OpcionesStickerService {

    private final TipoTroqueladoStickerRepository tipoTroqueladoStickerRepository;
    private final CantidadStickerRepository cantidadStickerRepository;
    private final MedidaStickerRepository medidaStickerRepository;

    public OpcionesStickerService(TipoTroqueladoStickerRepository tipoTroqueladoStickerRepository, CantidadStickerRepository cantidadStickerRepository, MedidaStickerRepository medidaStickerRepository) {
        this.tipoTroqueladoStickerRepository = tipoTroqueladoStickerRepository;
        this.cantidadStickerRepository = cantidadStickerRepository;
        this.medidaStickerRepository = medidaStickerRepository;
    }

    public List<TipoTroqueladoSticker> buscarTodosTipoTroqueladoSticker() {
        return tipoTroqueladoStickerRepository.findAll();
    }

    public List<CantidadSticker> buscarTodosCantidadSticker() {
        return cantidadStickerRepository.findAll();
    }

    public List<MedidaSticker> buscarTodosMedidaSticker() {
        return medidaStickerRepository.findAll();
    }
}
