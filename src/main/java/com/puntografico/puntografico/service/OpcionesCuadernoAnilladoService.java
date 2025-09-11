package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedidaCuadernoAnillado;
import com.puntografico.puntografico.domain.TipoTapaCuadernoAnillado;
import com.puntografico.puntografico.repository.MedidaCuadernoAnilladoRepository;
import com.puntografico.puntografico.repository.TipoTapaCuadernoAnilladoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional @AllArgsConstructor
public class OpcionesCuadernoAnilladoService {

    private final TipoTapaCuadernoAnilladoRepository tipoTapaCuadernoAnilladoRepository;
    private final MedidaCuadernoAnilladoRepository medidaCuadernoAnilladoRepository;

    public List<TipoTapaCuadernoAnillado> buscarTodosTipoTapaCuadernoAnillado() {
        return tipoTapaCuadernoAnilladoRepository.findAll();
    }

    public List<MedidaCuadernoAnillado> buscarTodosMedidaCuadernoAnillado() {
        return medidaCuadernoAnilladoRepository.findAll();
    }
}
