package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadSobre;
import com.puntografico.puntografico.domain.MedidaSobre;
import com.puntografico.puntografico.domain.Sobre;
import com.puntografico.puntografico.domain.TipoColorSobre;
import com.puntografico.puntografico.repository.CantidadSobreRepository;
import com.puntografico.puntografico.repository.MedidaSobreRepository;
import com.puntografico.puntografico.repository.SobreRepository;
import com.puntografico.puntografico.repository.TipoColorSobreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class SobreService {

    private final SobreRepository sobreRepository;

    private final MedidaSobreRepository medidaSobreRepository;

    private final TipoColorSobreRepository tipoColorSobreRepository;

    private final CantidadSobreRepository cantidadSobreRepository;

    public Sobre crear(HttpServletRequest request) {
        String medidaSobreString = request.getParameter("medidaSobre.id");
        String tipoColorSobreString = request.getParameter("tipoColorSobre.id");
        String cantidadSobreString = request.getParameter("cantidadSobre.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaSobreString, "medidaSobre es un dato obligatorio.");
        Assert.notNull(tipoColorSobreString, "tipoColorSobre es un dato obligatorio.");
        Assert.notNull(cantidadSobreString, "cantidadSobre es un dato obligatorio.");

        MedidaSobre medidaSobre = medidaSobreRepository.findById(Long.parseLong(medidaSobreString)).get();
        TipoColorSobre tipoColorSobre = tipoColorSobreRepository.findById(Long.parseLong(tipoColorSobreString)).get();
        CantidadSobre cantidadSobre = cantidadSobreRepository.findById(Long.parseLong(cantidadSobreString)).get();

        int cantidad;

        if (cantidadSobre.getId() != 4) {
            cantidad = Integer.parseInt(cantidadSobre.getCantidad());
        } else {
            cantidad = Integer.parseInt(cantidadString);
        }

        Sobre sobre = new Sobre();
        sobre.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        sobre.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        sobre.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        sobre.setInformacionAdicional(request.getParameter("informacionAdicional"));
        sobre.setMedidaSobre(medidaSobre);
        sobre.setTipoColorSobre(tipoColorSobre);
        sobre.setCantidadSobre(cantidadSobre);
        sobre.setCantidad(cantidad);

        return sobreRepository.save(sobre);
    }
}
