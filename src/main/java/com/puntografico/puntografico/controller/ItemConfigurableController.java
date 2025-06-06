package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.service.ItemConfigurableService;
import com.puntografico.puntografico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemConfigurableController {

    @Autowired
    private ItemConfigurableService itemConfigurableService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/item-configurable/crear")
    public String crear(
            @ModelAttribute ItemConfigurable itemConfigurable,
            @RequestParam Long productoId,
            @RequestParam String username,
            RedirectAttributes redirectAttributes
    ) {
        Producto producto = productoService.buscarPorId(productoId);
        ItemConfigurable itemConfigurableCreado = itemConfigurableService.crear(itemConfigurable, producto);

        redirectAttributes.addFlashAttribute("productoId", productoId);
        redirectAttributes.addFlashAttribute("itemConfigurableId", itemConfigurableCreado.getId());
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("producto", producto);

        return "redirect:/creacionProducto";
    }
}
