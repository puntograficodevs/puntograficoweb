package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.ItemConfigurableOpcion;
import com.puntografico.puntografico.repository.ItemConfigurableOpcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemConfigurableOpcionService {

    @Autowired
    private ItemConfigurableOpcionRepository itemConfigurableOpcionRepository;

    public ItemConfigurableOpcion crear(ItemConfigurableOpcion opcion, ItemConfigurable itemConfigurable) {
        opcion.setItemConfigurable(itemConfigurable);

        return itemConfigurableOpcionRepository.save(opcion);
    }
}
