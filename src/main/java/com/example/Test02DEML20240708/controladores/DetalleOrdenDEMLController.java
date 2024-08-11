package com.example.Test02DEML20240708.controladores;

import com.example.Test02DEML20240708.modelos.DetalleOrdenDEML;
import com.example.Test02DEML20240708.servicios.interfaces.IDetalleOrdenDEMLService;
import com.example.Test02DEML20240708.servicios.interfaces.IOrdenDEMLService;
import com.example.Test02DEML20240708.servicios.interfaces.IProductoDEMLService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/detalleOrdenes")
public class DetalleOrdenDEMLController {

    @Autowired
    private IDetalleOrdenDEMLService detalleOrdenDEMLService;

    @Autowired
    private IOrdenDEMLService ordenDEMLService;

    @Autowired
    private IProductoDEMLService productoDEMLService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<DetalleOrdenDEML> detalleOrdenes = detalleOrdenDEMLService.buscarTodosPaginados(pageable);
        model.addAttribute("detalleOrdenes", detalleOrdenes);

        int totalPages = detalleOrdenes.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "detalleOrden/index";
    }

    @GetMapping("/create")
    public String create(DetalleOrdenDEML detalleOrdenDEML, Model model) {
        model.addAttribute("productos", productoDEMLService.obtenerTodos());
        model.addAttribute("ordenes", ordenDEMLService.obtenerTodos());
        return "detalleOrden/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("detalleOrden") DetalleOrdenDEML detalleOrdenDEML, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("detalleOrden", detalleOrdenDEML);
            model.addAttribute("productos", productoDEMLService.obtenerTodos());
            model.addAttribute("ordenes", ordenDEMLService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "detalleOrden/edit"; // Cambiado para manejar errores en edición
        }

        detalleOrdenDEMLService.crearOEditar(detalleOrdenDEML);
        attributes.addFlashAttribute("msg", "Detalle Orden creada/modificada correctamente");
        return "redirect:/detalleOrdenes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenDEML> detalleOrdenDEML = detalleOrdenDEMLService.buscarPorId(id);
        if (detalleOrdenDEML.isPresent()) {
            model.addAttribute("detalleOrden", detalleOrdenDEML.get());
            model.addAttribute("productos", productoDEMLService.obtenerTodos());
            model.addAttribute("ordenes", ordenDEMLService.obtenerTodos());
        } else {
            return "redirect:/detalleOrdenes";
        }
        return "detalleOrden/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenDEML> detalleOrdenDEML = detalleOrdenDEMLService.buscarPorId(id);
        if (detalleOrdenDEML.isPresent()) {
            model.addAttribute("detalleOrden", detalleOrdenDEML.get());
            model.addAttribute("productos", productoDEMLService.obtenerTodos());
            model.addAttribute("ordenes", ordenDEMLService.obtenerTodos());
        } else {
            return "redirect:/detalleOrdenes";
        }
        return "detalleOrden/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Long id, Model model) {
        Optional<DetalleOrdenDEML> detalleOrdenDEML = detalleOrdenDEMLService.buscarPorId(id);
        if (detalleOrdenDEML.isPresent()) {
            model.addAttribute("detalleOrden", detalleOrdenDEML.get());
            model.addAttribute("productos", productoDEMLService.obtenerTodos());
            model.addAttribute("ordenes", ordenDEMLService.obtenerTodos());
        } else {
            return "redirect:/detalleOrdenes";
        }
        return "detalleOrden/delete";
    }

    @PostMapping("/delete")
    public String delete(DetalleOrdenDEML detalleOrdenDEML, RedirectAttributes attributes) {
        detalleOrdenDEMLService.eliminarPorId(detalleOrdenDEML.getId());
        attributes.addFlashAttribute("msg", "Detalle Orden eliminada correctamente");
        return "redirect:/detalleOrdenes";
    }

}
