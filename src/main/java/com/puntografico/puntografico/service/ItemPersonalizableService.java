package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ItemPersonalizable;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.repository.ItemPersonalizableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemPersonalizableService {

    @Autowired
    private ItemPersonalizableRepository itemPersonalizableRepository;

    public ItemPersonalizable crear(ItemPersonalizable itemPersonalizable, Producto producto) {
        itemPersonalizable.setProducto(producto);
        return itemPersonalizableRepository.save(itemPersonalizable);
    }

    public ItemPersonalizable buscarPorId(Long id) {
        return itemPersonalizableRepository.findById(id).get();
    }
}
