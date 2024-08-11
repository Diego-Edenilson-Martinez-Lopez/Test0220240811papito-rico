package com.example.Test02DEML20240708.servicios.implementaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Test02DEML20240708.modelos.ProductoDEML;
import com.example.Test02DEML20240708.repositorios.IProductoDEMLRepository;
import com.example.Test02DEML20240708.servicios.interfaces.IProductoDEMLService;

@Service
public class ProductoDEMLService implements IProductoDEMLService {

     @Autowired
    private IProductoDEMLRepository productoDEMLRepository;

    @Override
    public Page<ProductoDEML> buscarTodosPaginados(Pageable pageable) {
        return productoDEMLRepository.findAll(pageable);
    }

    @Override
    public List<ProductoDEML> obtenerTodos() {
        return productoDEMLRepository.findAll();
    }

    @Override
    public Optional<ProductoDEML> buscarPorId(Integer id) {
        return productoDEMLRepository.findById(id);
    }

    @Override
    public ProductoDEML crearOEditar(ProductoDEML productoDEML) {
        return productoDEMLRepository.save(productoDEML);
    }

    @Override
    public void eliminarPorId(Integer id) {
        productoDEMLRepository.deleteById(id);
    }

}
