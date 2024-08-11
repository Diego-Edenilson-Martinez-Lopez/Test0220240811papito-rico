package com.example.Test02DEML20240708.controladores;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.Test02DEML20240708.modelos.OrdenDEML;
import com.example.Test02DEML20240708.servicios.interfaces.IOrdenDEMLService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ordenes")
public class OrdenDEMLController {

    @Autowired
    private IOrdenDEMLService ordenDEMLService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<OrdenDEML> ordenes = ordenDEMLService.buscarTodosPaginados(pageable);
        model.addAttribute("ordenes", ordenes);

        int totalPages = ordenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "orden/index";
    }

    @GetMapping("/create")
    public String create(OrdenDEML ordenDEML){
        return "orden/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ordenDEML") OrdenDEML ordenDEML, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(ordenDEML);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "orden/create";
        }

        ordenDEMLService.crearOEditar(ordenDEML);
        attributes.addFlashAttribute("msg", "Orden creada/modificada correctamente");
        return "redirect:/ordenes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model){
        OrdenDEML ordenDEML = ordenDEMLService.buscarPorId(id).get();
        model.addAttribute("orden", ordenDEML);
        return "orden/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        OrdenDEML ordenDEML = ordenDEMLService.buscarPorId(id).get();
        model.addAttribute("orden", ordenDEML);
        return "orden/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model){
        OrdenDEML ordenDEML = ordenDEMLService.buscarPorId(id).get();
        model.addAttribute("orden", ordenDEML);
        return "orden/delete";
    }

    @PostMapping("/delete")
    public String delete(OrdenDEML ordenDEML, RedirectAttributes attributes){
        ordenDEMLService.eliminarPorId(ordenDEML.getId());
        attributes.addFlashAttribute("msg", "Orden eliminada correctamente");
        return "redirect:/ordenes";
    }

}
