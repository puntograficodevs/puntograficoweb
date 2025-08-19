package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Rol;
import com.puntografico.puntografico.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Rol buscarPorId(Long id) {
        return rolRepository.findById(id).get();
    }
}
