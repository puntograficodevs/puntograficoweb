package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.PlantillaCarpetaSolapa;
import com.puntografico.puntografico.repository.PlantillaCarpetaSolapaRepository;
import com.puntografico.puntografico.service.PlantillaCarpetaSolapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/plantilla-carpeta-solapa")
public class PlantillaCarpetaSolapaRestController {
    @Autowired
    private PlantillaCarpetaSolapaRepository plantillaCarpetaSolapaRepository;

    @Autowired
    private PlantillaCarpetaSolapaService plantillaCarpetaSolapaService;

    @GetMapping("/precio")
    public ResponseEntity<Integer> obtenerPrecio(
            @RequestParam int cantidad,
            @RequestParam Long tipoLaminadoCarpetaSolapaId,
            @RequestParam Long tipoFazCarpetaSolapaId) {

        Long cantidadCarpetaSolapaId = plantillaCarpetaSolapaService.buscarIdCantidadCarpetaSolapa(cantidad);

        Optional<PlantillaCarpetaSolapa> plantilla = plantillaCarpetaSolapaRepository
                .findByTipoLaminadoCarpetaSolapa_IdAndTipoFazCarpetaSolapa_IdAndCantidadCarpetaSolapa_Id(
                        tipoLaminadoCarpetaSolapaId, tipoFazCarpetaSolapaId, cantidadCarpetaSolapaId);

        return plantilla
                .map(p -> ResponseEntity.ok(p.getPrecio()))
                .orElse(ResponseEntity.noContent().build());
    }
}
