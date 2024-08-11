package com.example.Test02DEML20240708.controladores;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Test02DEML20240708.servicios.interfaces.IProductoDEMLService;
import com.example.Test02DEML20240708.modelos.ProductoDEML;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/productos")
public class ProductoDEMLController {

    @Autowired
    private IProductoDEMLService productoDEMLService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<ProductoDEML> productoDEMLS = productoDEMLService.buscarTodosPaginados(pageable);
        model.addAttribute("productos", productoDEMLS);

        int totalPages = productoDEMLS.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "producto/index";
    }

    @GetMapping("/create")
    public String create(ProductoDEML productoDEML) {
        return "producto/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("productoDEML") ProductoDEML productoDEML, BindingResult result,
            Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute(productoDEML);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "producto/create";
        }

        productoDEMLService.crearOEditar(productoDEML);
        attributes.addFlashAttribute("msg", "Producto creado/modificado correctamente");
        return "redirect:/productos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        ProductoDEML productoDEML = productoDEMLService.buscarPorId(id).get();
        model.addAttribute("producto", productoDEML);
        return "producto/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        ProductoDEML productoDEML = productoDEMLService.buscarPorId(id).get();
        model.addAttribute("producto", productoDEML);
        return "producto/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        ProductoDEML productoDEML = productoDEMLService.buscarPorId(id).get();
        model.addAttribute("producto", productoDEML);
        return "producto/delete";
    }

    @PostMapping("/delete")
    public String delete(ProductoDEML productoDEML, RedirectAttributes attributes) {
        productoDEMLService.eliminarPorId(productoDEML.getId());
        attributes.addFlashAttribute("msg", "Producto eliminado correctamente");
        return "redirect:/productos";
    }
}
