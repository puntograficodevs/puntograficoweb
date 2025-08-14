package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.LonaComun;
import com.puntografico.puntografico.domain.MedidaLonaComun;
import com.puntografico.puntografico.domain.TipoLonaComun;
import com.puntografico.puntografico.repository.LonaComunRepository;
import com.puntografico.puntografico.repository.MedidaLonaComunRepository;
import com.puntografico.puntografico.repository.TipoLonaComunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class LonaComunService {

    @Autowired
    private LonaComunRepository lonaComunRepository;

    @Autowired
    private MedidaLonaComunRepository medidaLonaComunRepository;

    @Autowired
    private TipoLonaComunRepository tipoLonaComunRepository;

    public LonaComun crear(HttpServletRequest request) {
        String medidaLonaComunString = request.getParameter("medidaLonaComun.id");
        String tipoLonaComunString = request.getParameter("tipoLonaComun.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(medidaLonaComunString, "La medida es un dato obligatorio.");
        Assert.notNull(tipoLonaComunString, "El tipo de lona es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");

        MedidaLonaComun medidaLonaComun = medidaLonaComunRepository.findById(Long.parseLong(medidaLonaComunString)).get();
        TipoLonaComun tipoLonaComun = tipoLonaComunRepository.findById(Long.parseLong(tipoLonaComunString)).get();

        LonaComun lonaComun = new LonaComun();
        lonaComun.setMedidaLonaComun(medidaLonaComun);
        lonaComun.setTipoLonaComun(tipoLonaComun);
        lonaComun.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        lonaComun.setConOjales(request.getParameter("conOjales") != null);
        lonaComun.setConOjalesConRefuerzo(request.getParameter("conOjalesConRefuerzo") != null);
        lonaComun.setConBolsillos(request.getParameter("conBolsillos") != null);
        lonaComun.setConDemasiaParaTensado(request.getParameter("conDemasiaParaTensado") != null);
        lonaComun.setConSolapado(request.getParameter("conSolapado") != null);
        lonaComun.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        lonaComun.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        lonaComun.setInformacionAdicional(request.getParameter("informacionAdicional"));
        lonaComun.setCantidad(Integer.parseInt(cantidadString));

        return lonaComunRepository.save(lonaComun);
    }

}
