package com.example.Test02DEML20240708.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Test02DEML20240708.modelos.OrdenDEML;
import com.example.Test02DEML20240708.repositorios.IOrdenDEMLRepository;
import com.example.Test02DEML20240708.servicios.interfaces.IOrdenDEMLService;

@Service
public class OrdenDEMLService implements IOrdenDEMLService {

    @Autowired
    private IOrdenDEMLRepository ordenDEMLRepository;

    @Override
    public Page<OrdenDEML> buscarTodosPaginados(Pageable pageable) {
        return ordenDEMLRepository.findAll(pageable);
    }

    @Override
    public List<OrdenDEML> obtenerTodos() {
        return ordenDEMLRepository.findAll();
    }

    @Override
    public Optional<OrdenDEML> buscarPorId(Long id) {
        return ordenDEMLRepository.findById(id.intValue());
    }

    @Override
    public OrdenDEML crearOEditar(OrdenDEML ordenEISG) {
        return ordenDEMLRepository.save(ordenEISG);
    }

    @Override
    public void eliminarPorId(Long id) {
        ordenDEMLRepository.deleteById(id.intValue());
    }

}
