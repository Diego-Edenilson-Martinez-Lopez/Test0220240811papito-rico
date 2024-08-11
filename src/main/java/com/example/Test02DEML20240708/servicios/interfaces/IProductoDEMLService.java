package com.example.Test02DEML20240708.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Test02DEML20240708.modelos.ProductoDEML;

public interface IProductoDEMLService {

    Page<ProductoDEML> buscarTodosPaginados(Pageable pageable);

    List<ProductoDEML> obtenerTodos();

    Optional<ProductoDEML> buscarPorId(Integer id);

    ProductoDEML crearOEditar(ProductoDEML productoDEML);

    void eliminarPorId(Integer id);

}
