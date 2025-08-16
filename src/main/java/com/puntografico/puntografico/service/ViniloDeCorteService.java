package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.TraeMaterialVinilo;
import com.puntografico.puntografico.domain.Vinilo;
import com.puntografico.puntografico.domain.ViniloDeCorte;
import com.puntografico.puntografico.repository.TraeMaterialViniloRepository;
import com.puntografico.puntografico.repository.ViniloDeCorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class ViniloDeCorteService {

    @Autowired
    private ViniloDeCorteRepository viniloDeCorteRepository;

    @Autowired
    private TraeMaterialViniloRepository traeMaterialViniloRepository;

    public ViniloDeCorte crear(HttpServletRequest request) {
        String traeMaterialViniloString = request.getParameter("traeMaterialVinilo.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(traeMaterialViniloString, "traeMaterialViniloString es un dato obligatorio.");
        Assert.notNull(cantidadString, "cantidadString es un dato obligatorio.");

        TraeMaterialVinilo traeMaterialVinilo = traeMaterialViniloRepository.findById(Long.parseLong(traeMaterialViniloString)).get();
        int cantidad = Integer.parseInt(cantidadString);

        ViniloDeCorte viniloDeCorte = new ViniloDeCorte();
        viniloDeCorte.setEsPromocional(request.getParameter("esPromocional") != null);
        viniloDeCorte.setEsOracal(request.getParameter("esOracal") != null);
        viniloDeCorte.setCodigoColor(request.getParameter("codigoColor"));
        viniloDeCorte.setConColocacion(request.getParameter("conColocacion") != null);
        viniloDeCorte.setMedida(request.getParameter("medida"));
        viniloDeCorte.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        viniloDeCorte.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        viniloDeCorte.setInformacionAdicional(request.getParameter("informacionAdicional"));
        viniloDeCorte.setTraeMaterialVinilo(traeMaterialVinilo);
        viniloDeCorte.setCantidad(cantidad);

        return viniloDeCorteRepository.save(viniloDeCorte);
    }
}
