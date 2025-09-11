package com.puntografico.puntografico.service;

import com.puntografico.puntografico.domain.CuadernoAnillado;
import com.puntografico.puntografico.domain.MedidaCuadernoAnillado;
import com.puntografico.puntografico.domain.TipoTapaCuadernoAnillado;
import com.puntografico.puntografico.repository.CuadernoAnilladoRepository;
import com.puntografico.puntografico.repository.MedidaCuadernoAnilladoRepository;
import com.puntografico.puntografico.repository.TipoTapaCuadernoAnilladoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service @Transactional @AllArgsConstructor
public class CuadernoAnilladoService {

    private final CuadernoAnilladoRepository cuadernoAnilladoRepository;

    private final TipoTapaCuadernoAnilladoRepository tipoTapaCuadernoAnilladoRepository;

    private final MedidaCuadernoAnilladoRepository medidaCuadernoAnilladoRepository;

    public CuadernoAnillado crear(HttpServletRequest request) {
        String cantidadHojasString = request.getParameter("cantidadHojas");
        String cantidadString = request.getParameter("cantidad");
        String tipoTapaCuadernoAnilladoString = request.getParameter("tipoTapaCuadernoAnillado.id");
        String medidaCuadernoAnilladoString = request.getParameter("medidaCuadernoAnillado.id");

        Assert.notNull(cantidadHojasString, "La cantidad de hojas es un dato obligatorio.");
        Assert.notNull(cantidadString, "La cantidad es un dato obligatorio.");
        Assert.notNull(tipoTapaCuadernoAnilladoString, "El tipo de tapa es un dato obligatorio.");
        Assert.notNull(medidaCuadernoAnilladoString, "La medida del cuaderno es un dato obligatorio.");

        CuadernoAnillado cuadernoAnillado = new CuadernoAnillado();
        TipoTapaCuadernoAnillado tipoTapaCuadernoAnillado = tipoTapaCuadernoAnilladoRepository.findById(Long.parseLong(tipoTapaCuadernoAnilladoString)).get();
        MedidaCuadernoAnillado medidaCuadernoAnillado = medidaCuadernoAnilladoRepository.findById(Long.parseLong(medidaCuadernoAnilladoString)).get();

        cuadernoAnillado.setMedidaPersonalizada(request.getParameter("medidaPersonalizada"));
        cuadernoAnillado.setTipoTapaPersonalizada(request.getParameter("tipoTapaPersonalizada"));
        cuadernoAnillado.setCantidadHojas(Integer.parseInt(cantidadHojasString));
        cuadernoAnillado.setEnlaceArchivo(request.getParameter("enlaceArchivo"));
        cuadernoAnillado.setConAdicionalDisenio(request.getParameter("conAdicionalDisenio") != null);
        cuadernoAnillado.setInformacionAdicional(request.getParameter("informacionAdicional"));
        cuadernoAnillado.setCantidad(Integer.parseInt(cantidadString));
        cuadernoAnillado.setTipoTapaCuadernoAnillado(tipoTapaCuadernoAnillado);
        cuadernoAnillado.setMedidaCuadernoAnillado(medidaCuadernoAnillado);

        return cuadernoAnilladoRepository.save(cuadernoAnillado);
    }
}
