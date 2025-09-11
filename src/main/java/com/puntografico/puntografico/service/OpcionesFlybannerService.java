package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.AlturaFlybanner;
import com.puntografico.puntografico.domain.BanderaFlybanner;
import com.puntografico.puntografico.domain.TipoBaseFlybanner;
import com.puntografico.puntografico.domain.TipoFazFlybanner;
import com.puntografico.puntografico.repository.AlturaFlybannerRepository;
import com.puntografico.puntografico.repository.BanderaFlybannerRepository;
import com.puntografico.puntografico.repository.TipoBaseFlybannerRepository;
import com.puntografico.puntografico.repository.TipoFazFlybannerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesFlybannerService {

    private final TipoFazFlybannerRepository tipoFazFlybannerRepository;
    private final AlturaFlybannerRepository alturaFlybannerRepository;
    private final BanderaFlybannerRepository banderaFlybannerRepository;
    private final TipoBaseFlybannerRepository tipoBaseFlybannerRepository;

    public List<TipoFazFlybanner> buscarTodosTipoFazFlybaner() {
        return tipoFazFlybannerRepository.findAll();
    }

    public List<AlturaFlybanner> buscarTodosAlturaFlybanner() {
        return alturaFlybannerRepository.findAll();
    }

    public List<BanderaFlybanner> buscarTodosBanderaFlybanner() {
        return banderaFlybannerRepository.findAll();
    }

    public List<TipoBaseFlybanner> buscarTodosTipoBaseFlybanner() {
        return tipoBaseFlybannerRepository.findAll();
    }
}
