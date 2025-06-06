package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.ItemConfigurableOpcion;
import com.puntografico.puntografico.service.ItemConfigurableOpcionService;
import com.puntografico.puntografico.service.ItemConfigurableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemConfigurableOpcionController {

    @Autowired
    private ItemConfigurableOpcionService itemConfigurableOpcionService;

    @Autowired
    private ItemConfigurableService itemConfigurableService;

    @PostMapping("/item-configurable-opcion/crear")
    public String crear(@ModelAttribute ItemConfigurableOpcion opcion, @RequestParam("itemConfigurableId") Long itemConfigurableId) {
        ItemConfigurable itemConfigurable = itemConfigurableService.buscarPorId(itemConfigurableId);

        itemConfigurableOpcionService.crear(opcion, itemConfigurable);

        return "redirect:/creacionProducto";
    }
}
