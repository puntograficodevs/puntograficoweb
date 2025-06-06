package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.ItemConfigurableOpcion;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.service.ItemConfigurableOpcionService;
import com.puntografico.puntografico.service.ItemConfigurableService;
import com.puntografico.puntografico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemConfigurableOpcionController {

    @Autowired
    private ItemConfigurableOpcionService itemConfigurableOpcionService;

    @Autowired
    private ItemConfigurableService itemConfigurableService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/item-configurable-opcion/crear")
    public String crear(@ModelAttribute ItemConfigurableOpcion opcion,
                        @RequestParam("itemConfigurableId") Long itemConfigurableId,
                        @RequestParam String username,
                        RedirectAttributes redirectAttributes) {
        ItemConfigurable itemConfigurable = itemConfigurableService.buscarPorId(itemConfigurableId);
        Producto producto = itemConfigurable.getProducto();
        itemConfigurableOpcionService.crear(opcion, itemConfigurable);

        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("producto", producto);

        return "redirect:/creacionProducto";
    }
}
