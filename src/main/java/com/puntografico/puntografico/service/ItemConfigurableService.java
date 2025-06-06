package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.repository.ItemConfigurableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemConfigurableService {

    @Autowired
    private ItemConfigurableRepository itemConfigurableRepository;

    public ItemConfigurable crear(ItemConfigurable itemConfigurable, Producto producto) {
        itemConfigurable.setProducto(producto);
        return itemConfigurableRepository.save(itemConfigurable);
    }

    public ItemConfigurable buscarPorId(Long id) {
        return itemConfigurableRepository.findById(id).get();
    }
}
