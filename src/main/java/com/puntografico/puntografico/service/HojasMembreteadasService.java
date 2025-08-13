package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CantidadHojasMembreteadas;
import com.puntografico.puntografico.domain.HojasMembreteadas;
import com.puntografico.puntografico.domain.MedidaHojasMembreteadas;
import com.puntografico.puntografico.domain.TipoColorHojasMembreteadas;
import com.puntografico.puntografico.repository.CantidadHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.HojasMembreteadasRepository;
import com.puntografico.puntografico.repository.MedidaHojasMembreteadasRepository;
import com.puntografico.puntografico.repository.TipoColorHojasMembreteadasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class HojasMembreteadasService {

    @Autowired
    private MedidaHojasMembreteadasRepository medidaHojasMembreteadasRepository;

    @Autowired
    private TipoColorHojasMembreteadasRepository tipoColorHojasMembreteadasRepository;

    @Autowired
    private CantidadHojasMembreteadasRepository cantidadHojasMembreteadasRepository;

    @Autowired
    private HojasMembreteadasRepository hojasMembreteadasRepository;

    public HojasMembreteadas crear(HttpServletRequest request) {
        String cantidadHojasString = request.getParameter("cantidadHojas");
        String medidaHojasMembreteadasString = request.getParameter("medidaHojasMembreteadas.id");
        String tipoColorHojasMembreteadasString = request.getParameter("tipoColorHojasMembreteadas.id");
        String cantidadHojasMembreteadasString = request.getParameter("cantidadHojasMembreteadas.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaHojasMembreteadasString, "medidaHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(tipoColorHojasMembreteadasString, "tipoColorHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(cantidadHojasMembreteadasString, "cantidadHojasMembreteadasString es un dato obligatorio.");
        Assert.notNull(cantidadHojasString, "cantidadHojas es un dato obligatorio.");

        MedidaHojasMembreteadas medidaHojasMembreteadas = medidaHojasMembreteadasRepository.findById(Long.parseLong(medidaHojasMembreteadasString)).get();
        TipoColorHojasMembreteadas tipoColorHojasMembreteadas = tipoColorHojasMembreteadasRepository.findById(Long.parseLong(tipoColorHojasMembreteadasString)).get();
        CantidadHojasMembreteadas cantidadHojasMembreteadas = cantidadHojasMembreteadasRepository.findById(Long.parseLong(cantidadHojasMembreteadasString)).get();

        if (cantidadString.isBlank() || cantidadString.isEmpty()) {
            cantidadString = cantidadHojasMembreteadas.getCantidad();
        }

        HojasMembreteadas hojasMembreteadas = new HojasMembreteadas();
        hojasMembreteadas.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        hojasMembreteadas.setCantidadHojas(Integer.parseInt(cantidadHojasString));
        hojasMembreteadas.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        hojasMembreteadas.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        hojasMembreteadas.setInformacionAdicional(request.getParameter("informacionAdicional"));
        hojasMembreteadas.setMedidaHojasMembreteadas(medidaHojasMembreteadas);
        hojasMembreteadas.setTipoColorHojasMembreteadas(tipoColorHojasMembreteadas);
        hojasMembreteadas.setCantidadHojasMembreteadas(cantidadHojasMembreteadas);
        hojasMembreteadas.setCantidad(Integer.parseInt(cantidadString));

        return hojasMembreteadasRepository.save(hojasMembreteadas);
    }
}
