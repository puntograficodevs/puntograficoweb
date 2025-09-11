package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.*;
import com.puntografico.puntografico.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class EtiquetaService {

    private final EtiquetaRepository etiquetaRepository;

    private final TipoPapelEtiquetaRepository tipoPapelEtiquetaRepository;

    private final TipoLaminadoEtiquetaRepository tipoLaminadoEtiquetaRepository;

    private final TamanioPerforacionRepository tamanioPerforacionRepository;

    private final TipoFazEtiquetaRepository tipoFazEtiquetaRepository;

    private final CantidadEtiquetaRepository cantidadEtiquetaRepository;

    private final MedidaEtiquetaRepository medidaEtiquetaRepository;

    public Etiqueta crear(HttpServletRequest request) {
        String tipoPapelEtiquetaString = request.getParameter("tipoPapelEtiqueta.id");
        String tipoLaminadoEtiquetaString = request.getParameter("tipoLaminadoEtiqueta.id");
        String tamanioPerforacionString = request.getParameter("tamanioPerforacion.id");
        String tipoFazEtiquetaString = request.getParameter("tipoFazEtiqueta.id");
        String cantidadEtiquetaString = request.getParameter("cantidadEtiqueta.id");
        String medidaEtiquetaString = request.getParameter("medidaEtiqueta.id");
        String cantidadString = request.getParameter("cantidad");

        Assert.notNull(tipoPapelEtiquetaString, "El tipo de papel es un dato obligatorio.");
        Assert.notNull(tipoLaminadoEtiquetaString, "El tipo de laminado es un dato obligatorio.");
        Assert.notNull(tamanioPerforacionString, "El tamaño de la perforación es un dato obligatorio.");
        Assert.notNull(tipoFazEtiquetaString,"El tipo de faz es un dato obligatorio.");
        Assert.notNull(medidaEtiquetaString, "La medida es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");

        TipoPapelEtiqueta tipoPapelEtiqueta = tipoPapelEtiquetaRepository.findById(Long.parseLong(tipoPapelEtiquetaString)).get();
        TipoLaminadoEtiqueta tipoLaminadoEtiqueta = tipoLaminadoEtiquetaRepository.findById(Long.parseLong(tipoLaminadoEtiquetaString)).get();
        TamanioPerforacion tamanioPerforacion = tamanioPerforacionRepository.findById(Long.parseLong(tamanioPerforacionString)).get();
        TipoFazEtiqueta tipoFazEtiqueta = tipoFazEtiquetaRepository.findById(Long.parseLong(tipoFazEtiquetaString)).get();
        CantidadEtiqueta cantidadEtiqueta = cantidadEtiquetaRepository.findById(Long.parseLong(cantidadEtiquetaString)).get();
        MedidaEtiqueta medidaEtiqueta = medidaEtiquetaRepository.findById(Long.parseLong(medidaEtiquetaString)).get();

        if (cantidadString.isBlank() || cantidadString.isEmpty()) {
            cantidadString = cantidadEtiqueta.getCantidad();
        }

        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        etiqueta.setConPerforacionAdicional(request.getParameter("conPerforacionAdicional") != null);
        etiqueta.setConMarcaAdicional(request.getParameter("conMarcaAdicional") != null);
        etiqueta.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        etiqueta.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        etiqueta.setInformacionAdicional(request.getParameter("informacionAdicional"));
        etiqueta.setTipoPapelEtiqueta(tipoPapelEtiqueta);
        etiqueta.setTipoLaminadoEtiqueta(tipoLaminadoEtiqueta);
        etiqueta.setTamanioPerforacion(tamanioPerforacion);
        etiqueta.setTipoFazEtiqueta(tipoFazEtiqueta);
        etiqueta.setCantidadEtiqueta(cantidadEtiqueta);
        etiqueta.setMedidaEtiqueta(medidaEtiqueta);
        etiqueta.setCantidad(Integer.parseInt(cantidadString));

        return etiquetaRepository.save(etiqueta);
    }
}
