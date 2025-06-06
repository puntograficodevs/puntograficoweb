package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crear(String nombre) {
        Producto producto = new Producto();
        producto.setNombre(nombre);
        return productoRepository.save(producto);
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).get();
    }
}
