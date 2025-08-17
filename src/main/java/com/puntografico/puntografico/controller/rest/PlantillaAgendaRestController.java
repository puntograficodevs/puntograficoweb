package com.puntografico.puntografico.controller.rest;

import com.puntografico.puntografico.domain.PlantillaAgenda;
import com.puntografico.puntografico.repository.PlantillaAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/plantilla-agenda")
public class PlantillaAgendaRestController {

    @Autowired
    private PlantillaAgendaRepository plantillaAgendaRepository;

    @GetMapping("/precio")
    public ResponseEntity<Integer> obtenerPrecio(
            @RequestParam int cantidadHojas,
            @RequestParam Long tipoTapaId,
            @RequestParam Long tipoColorId) {

        Optional<PlantillaAgenda> plantilla = plantillaAgendaRepository
                .findByCantidadHojasAndTipoTapaAgenda_IdAndTipoColorAgenda_Id(
                        cantidadHojas, tipoTapaId, tipoColorId);

        return plantilla
                .map(p -> ResponseEntity.ok(p.getPrecio()))
                .orElse(ResponseEntity.noContent().build());
    }
}
