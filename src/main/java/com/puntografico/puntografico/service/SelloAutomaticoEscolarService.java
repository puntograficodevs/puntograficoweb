package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.ModeloSelloAutomatico;
import com.puntografico.puntografico.domain.ModeloSelloAutomaticoEscolar;
import com.puntografico.puntografico.domain.SelloAutomatico;
import com.puntografico.puntografico.domain.SelloAutomaticoEscolar;
import com.puntografico.puntografico.repository.ModeloSelloAutomaticoEscolarRepository;
import com.puntografico.puntografico.repository.ModeloSelloAutomaticoRepository;
import com.puntografico.puntografico.repository.SelloAutomaticoEscolarRepository;
import com.puntografico.puntografico.repository.SelloAutomaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@Transactional
public class SelloAutomaticoEscolarService {

    @Autowired
    private SelloAutomaticoEscolarRepository selloAutomaticoEscolarRepository;

    @Autowired
    private ModeloSelloAutomaticoEscolarRepository modeloSelloAutomaticoEscolarRepository;

    public SelloAutomaticoEscolar crear(HttpServletRequest request) {
        String textoLineaUno = request.getParameter("textoLineaUno");
        String tipografia = request.getParameter("tipografia");
        String dibujo = request.getParameter("dibujo");
        String modeloSelloAutomaticoEscolarString = request.getParameter("modeloSelloAutomaticoEscolar.id");
        String cantidad = request.getParameter("cantidad");

        Assert.notNull(textoLineaUno, "El texto de la primera línea es obligatorio.");
        Assert.notNull(tipografia, "La tipografía es obligatoria.");
        Assert.notNull(dibujo, "El dibujo es obligatorio.");
        Assert.notNull(modeloSelloAutomaticoEscolarString, "El modelo es un dato obligatorio.");
        Assert.notNull(cantidad, "La cantidad es un dato obligatorio.");

        ModeloSelloAutomaticoEscolar modeloSelloAutomaticoEscolar = modeloSelloAutomaticoEscolarRepository.findById(Long.parseLong(modeloSelloAutomaticoEscolarString)).get();

        SelloAutomaticoEscolar selloAutomaticoEscolar = new SelloAutomaticoEscolar();
        selloAutomaticoEscolar.setTextoLineaUno(textoLineaUno);
        selloAutomaticoEscolar.setTextoLineaDos(request.getParameter("textoLineaDos"));
        selloAutomaticoEscolar.setTextoLineaTres(request.getParameter("textoLineaTres"));
        selloAutomaticoEscolar.setTipografia(tipografia);
        selloAutomaticoEscolar.setDibujo(dibujo);
        selloAutomaticoEscolar.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        selloAutomaticoEscolar.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        selloAutomaticoEscolar.setInformacionAdicional(request.getParameter("informacionAdicional"));
        selloAutomaticoEscolar.setModeloSelloAutomaticoEscolar(modeloSelloAutomaticoEscolar);
        selloAutomaticoEscolar.setCantidad(Integer.parseInt(cantidad));

        return selloAutomaticoEscolarRepository.save(selloAutomaticoEscolar);
    }
}
