package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedioPago;
import com.puntografico.puntografico.repository.MedioPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MedioPagoService {

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    public List<MedioPago> buscarTodos() {
        return medioPagoRepository.findAll();
    }
}
