package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ModeloSelloAutomatico;
import com.puntografico.puntografico.domain.SelloAutomatico;
import com.puntografico.puntografico.repository.ModeloSelloAutomaticoRepository;
import com.puntografico.puntografico.repository.SelloAutomaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional
public class SelloAutomaticoService {

    @Autowired
    private SelloAutomaticoRepository selloAutomaticoRepository;

    @Autowired
    private ModeloSelloAutomaticoRepository modeloSelloAutomaticoRepository;

    public SelloAutomatico crear(HttpServletRequest request) {
        String textoLineaUno = request.getParameter("textoLineaUno");
        String tipografiaLineaUno = request.getParameter("tipografiaLineaUno");
        String modeloSelloAutomaticoString = request.getParameter("modeloSelloAutomatico.id");
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(textoLineaUno, "El texto de la primera línea es obligatorio.");
        Assert.notNull(tipografiaLineaUno, "La tipografía de la primer línea es obligatoria.");
        Assert.notNull(modeloSelloAutomaticoString, "El modelo es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");

        ModeloSelloAutomatico modeloSelloAutomatico = modeloSelloAutomaticoRepository.findById(Long.parseLong(modeloSelloAutomaticoString)).get();

        SelloAutomatico selloAutomatico = new SelloAutomatico();
        selloAutomatico.setEsProfesional(request.getParameter("esProfesional") != null);
        selloAutomatico.setEsParticular(request.getParameter("esParticular") != null);
        selloAutomatico.setTextoLineaUno(textoLineaUno);
        selloAutomatico.setTextoLineaDos(request.getParameter("textoLineaDos"));
        selloAutomatico.setTextoLineaTres(request.getParameter("textoLineaTres"));
        selloAutomatico.setTextoLineaCuatro(request.getParameter("textoLineaCuatro"));
        selloAutomatico.setTipografiaLineaUno(tipografiaLineaUno);
        selloAutomatico.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        selloAutomatico.setInformacionAdicional(request.getParameter("informacionAdicional"));
        selloAutomatico.setModeloSelloAutomatico(modeloSelloAutomatico);
        selloAutomatico.setCantidad(Integer.parseInt(cantidad));

        return selloAutomaticoRepository.save(selloAutomatico);
    }
}
