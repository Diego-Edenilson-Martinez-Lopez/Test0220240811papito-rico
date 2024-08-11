package com.example.Test02DEML20240708.servicios.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Test02DEML20240708.modelos.OrdenDEML;

public interface IOrdenDEMLService {

    Page<OrdenDEML> buscarTodosPaginados(Pageable pageable);

    List<OrdenDEML> obtenerTodos();

    Optional<OrdenDEML> buscarPorId(Long id);

    OrdenDEML crearOEditar(OrdenDEML ordenDEML);

    void eliminarPorId(Long id);
}
