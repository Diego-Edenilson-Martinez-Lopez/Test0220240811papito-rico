package com.example.Test02DEML20240708.modelos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "productos")
public class ProductoDEML {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotBlank(message = "El nombre es requerido")
    private String nombreDEML;

    @OneToMany(mappedBy = "productoDEML", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleOrdenDEML> detalleDEMLOrdenes = new HashSet<>();

    public ProductoDEML() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreDEML() {
        return nombreDEML;
    }

    public void setNombreDEML(String nombreDEML) {
        this.nombreDEML = nombreDEML;
    }

    public Set<DetalleOrdenDEML> getDetalleDEMLOrdenes() {
        return detalleDEMLOrdenes;
    }

    public void setDetalleDEMLOrdenes(Set<DetalleOrdenDEML> detalleDEMLOrdenes) {
        this.detalleDEMLOrdenes = detalleDEMLOrdenes;
    }

    
}
