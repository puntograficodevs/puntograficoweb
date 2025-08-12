package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class FlybannerService {

    @Autowired
    private TipoFazFlybannerRepository tipoFazFlybannerRepository;

    @Autowired
    private AlturaFlybannerRepository alturaFlybannerRepository;

    @Autowired
    private BanderaFlybannerRepository banderaFlybannerRepository;

    @Autowired
    private TipoBaseFlybannerRepository tipoBaseFlybannerRepository;

    @Autowired
    private FlybannerRepository flybannerRepository;

    public Flybanner crear(HttpServletRequest request) {
        String tipoFazFlybannerString = request.getParameter("tipoFazFlybanner.id");
        String alturaFlybannerString = request.getParameter("alturaFlybanner.id");
        String banderaFlybannerString = request.getParameter("banderaFlybanner.id");
        String tipoBaseFlybannerString = request.getParameter("tipoBaseFlybanner.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoFazFlybannerString, "tipoFazFlybannerString es un dato obligatorio.");
        Assert.notNull(alturaFlybannerString, "alturaFlybannerString es un dato obligatorio.");
        Assert.notNull(banderaFlybannerString, "banderaFlybannerString es un dato obligatorio.");
        Assert.notNull(tipoBaseFlybannerString, "tipoBaseFlybannerString es un dato obligatorio.");
        Assert.notNull(cantidadString, "cantidadString es un dato obligatorio.");

        TipoFazFlybanner tipoFazFlybanner = tipoFazFlybannerRepository.findById(Long.parseLong(tipoFazFlybannerString)).get();
        AlturaFlybanner alturaFlybanner = alturaFlybannerRepository.findById(Long.parseLong(alturaFlybannerString)).get();
        BanderaFlybanner banderaFlybanner = banderaFlybannerRepository.findById(Long.parseLong(banderaFlybannerString)).get();
        TipoBaseFlybanner tipoBaseFlybanner = tipoBaseFlybannerRepository.findById(Long.parseLong(tipoBaseFlybannerString)).get();

        Flybanner flybanner = new Flybanner();
        flybanner.setTipoFazFlybanner(tipoFazFlybanner);
        flybanner.setAlturaFlybanner(alturaFlybanner);
        flybanner.setBanderaFlybanner(banderaFlybanner);
        flybanner.setTipoBaseFlybanner(tipoBaseFlybanner);
        flybanner.setCantidad(Integer.parseInt(cantidadString));
        flybanner.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        flybanner.setInformacionAdicional(request.getParameter("informacionAdicional"));
        flybanner.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);

        return flybannerRepository.save(flybanner);
    }
}
