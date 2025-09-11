package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.MedidaViniloPlasticoCorrugado;
import com.puntografico.puntografico.domain.ViniloPlasticoCorrugado;
import com.puntografico.puntografico.repository.MedidaViniloPlasticoCorrugadoRepository;
import com.puntografico.puntografico.repository.ViniloPlasticoCorrugadoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class ViniloPlasticoCorrugadoService {

    private final ViniloPlasticoCorrugadoRepository viniloPlasticoCorrugadoRepository;

    private final MedidaViniloPlasticoCorrugadoRepository medidaViniloPlasticoCorrugadoRepository;

    public ViniloPlasticoCorrugado crear(HttpServletRequest request) {
        String medidaViniloPlasticoCorrugadoString = request.getParameter("medidaViniloPlasticoCorrugado.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaViniloPlasticoCorrugadoString, "medidaViniloPlasticoCorrugadoString es un dato obligatorio.");
        Assert.notNull(cantidadString, "cantidadString es un dato obligatorio.");

        MedidaViniloPlasticoCorrugado medidaViniloPlasticoCorrugado = medidaViniloPlasticoCorrugadoRepository.findById(Long.parseLong(medidaViniloPlasticoCorrugadoString)).get();

        ViniloPlasticoCorrugado viniloPlasticoCorrugado = new ViniloPlasticoCorrugado();
        viniloPlasticoCorrugado.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        viniloPlasticoCorrugado.setConOjales(request.getParameter("conOjales") != null);
        viniloPlasticoCorrugado.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        viniloPlasticoCorrugado.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        viniloPlasticoCorrugado.setInformacionAdicional(request.getParameter("informacionAdicional"));
        viniloPlasticoCorrugado.setMedidaViniloPlasticoCorrugado(medidaViniloPlasticoCorrugado);
        viniloPlasticoCorrugado.setCantidad(Integer.parseInt(cantidadString));

        return viniloPlasticoCorrugadoRepository.save(viniloPlasticoCorrugado);
    }
}
