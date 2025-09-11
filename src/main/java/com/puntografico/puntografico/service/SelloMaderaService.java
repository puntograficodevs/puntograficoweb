package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.SelloMadera;
import com.puntografico.puntografico.domain.TamanioSelloMadera;
import com.puntografico.puntografico.repository.SelloMaderaRepository;
import com.puntografico.puntografico.repository.TamanioSelloMaderaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class SelloMaderaService {

    private final SelloMaderaRepository selloMaderaRepository;

    private final TamanioSelloMaderaRepository tamanioSelloMaderaRepository;

    public SelloMadera crear(HttpServletRequest request) {

        String tamanioSelloMaderaString = request.getParameter("tamanioSelloMadera.id");
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(tamanioSelloMaderaString, "El tamaño es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");

        TamanioSelloMadera tamanioSelloMadera = tamanioSelloMaderaRepository.findById(Long.parseLong(tamanioSelloMaderaString)).get();

        SelloMadera selloMadera = new SelloMadera();
        selloMadera.setTamanioPersonalizado(request.getParameter("tamanioPersonalizado"));
        selloMadera.setConAdicionalPerilla(request.getParameter("conAdicionalPerilla") != null);
        selloMadera.setDetalleSello(request.getParameter("detalleSello"));
        selloMadera.setTipografiaLineaUno(request.getParameter("tipografiaLineaUno"));
        selloMadera.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        selloMadera.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        selloMadera.setInformacionAdicional(request.getParameter("informacionAdicional"));
        selloMadera.setTamanioSelloMadera(tamanioSelloMadera);
        selloMadera.setCantidad(Integer.parseInt(cantidad));

        return selloMaderaRepository.save(selloMadera);
    }
}
