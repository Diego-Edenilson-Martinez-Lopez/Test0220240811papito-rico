package com.example.Test02DEML20240708.modelos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;


import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ordenes")
public class OrdenDEML {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es requerida")
    private Date fecha;

    @OneToMany(mappedBy = "ordenDEML", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetalleOrdenDEML> detalleDEMLOrdenes = new HashSet<>();

    public OrdenDEML() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<DetalleOrdenDEML> getDetalleDEMLOrdenes() {
        return detalleDEMLOrdenes;
    }

    public void setDetalleDEMLOrdenes(Set<DetalleOrdenDEML> detalleDEMLOrdenes) {
        this.detalleDEMLOrdenes = detalleDEMLOrdenes;
    }



}
