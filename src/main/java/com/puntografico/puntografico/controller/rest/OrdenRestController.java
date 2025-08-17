package com.puntografico.puntografico.controller.rest;

import com.puntografico.puntografico.domain.OrdenAgenda;
import com.puntografico.puntografico.service.OrdenAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orden")
public class OrdenRestController {

    @Autowired
    private OrdenAgendaService ordenAgendaService;

    @GetMapping("/ordenAgenda/{ordenId}")
    public ResponseEntity<OrdenAgenda> getOrdenAgenda(@PathVariable Long ordenId) {
        OrdenAgenda ordenAgenda = ordenAgendaService.buscarPorOrdenId(ordenId);

        if (ordenAgenda != null) {
            return ResponseEntity.ok(ordenAgenda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
