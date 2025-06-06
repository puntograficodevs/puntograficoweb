package com.puntografico.puntografico.controller;

import com.puntografico.puntografico.domain.ItemConfigurable;
import com.puntografico.puntografico.domain.ItemPersonalizable;
import com.puntografico.puntografico.domain.Producto;
import com.puntografico.puntografico.service.ItemPersonalizableService;
import com.puntografico.puntografico.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemPersonalizableController {

    @Autowired
    private ItemPersonalizableService itemPersonalizableService;

    @Autowired
    private ProductoService productoService;

    @PostMapping("/item-personalizable/crear")
    public String crear(
            @ModelAttribute ItemPersonalizable itemPersonalizable,
            @RequestParam Long productoId,
            @RequestParam String username,
            RedirectAttributes redirectAttributes
    ) {
        Producto producto = productoService.buscarPorId(productoId);
        itemPersonalizableService.crear(itemPersonalizable, producto);

        redirectAttributes.addFlashAttribute("productoId", productoId);
        redirectAttributes.addAttribute("username", username);
        redirectAttributes.addAttribute("producto", producto);

        return "redirect:/creacionProducto";
    }
}
