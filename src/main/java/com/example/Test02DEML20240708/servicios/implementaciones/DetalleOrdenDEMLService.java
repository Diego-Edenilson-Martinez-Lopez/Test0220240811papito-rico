package com.example.Test02DEML20240708.servicios.implementaciones;

import com.example.Test02DEML20240708.modelos.DetalleOrdenDEML;
import com.example.Test02DEML20240708.repositorios.IDetalleOrdenDEMLRepository;
import com.example.Test02DEML20240708.servicios.interfaces.IDetalleOrdenDEMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenDEMLService implements IDetalleOrdenDEMLService {

    @Autowired
    private IDetalleOrdenDEMLRepository detalleOrdenDEMLRespository;

    @Override
    public Page<DetalleOrdenDEML> buscarTodosPaginados(Pageable pageable) {
        return detalleOrdenDEMLRespository.findAll(pageable);
    }

    @Override
    public List<DetalleOrdenDEML> obtenerTodos() {
        return detalleOrdenDEMLRespository.findAll();
    }

    @Override
    public Optional<DetalleOrdenDEML> buscarPorId(Long id) {
        return detalleOrdenDEMLRespository.findById(id.intValue());
    }

    @Override
    public DetalleOrdenDEML crearOEditar(DetalleOrdenDEML detalleOrdenDEML) {
        return detalleOrdenDEMLRespository.save(detalleOrdenDEML);
    }

    @Override
    public void eliminarPorId(Long id) {
        detalleOrdenDEMLRespository.deleteById(id.intValue());
    }

}
