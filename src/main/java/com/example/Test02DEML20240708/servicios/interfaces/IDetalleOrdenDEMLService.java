package com.example.Test02DEML20240708.servicios.interfaces;

import com.example.Test02DEML20240708.modelos.DetalleOrdenDEML;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IDetalleOrdenDEMLService {

    Page<DetalleOrdenDEML> buscarTodosPaginados(Pageable pageable);

    List<DetalleOrdenDEML> obtenerTodos();

    Optional<DetalleOrdenDEML> buscarPorId(Long id);

    DetalleOrdenDEML crearOEditar(DetalleOrdenDEML detalleOrdenDEML);

    void eliminarPorId(Long id);

}
