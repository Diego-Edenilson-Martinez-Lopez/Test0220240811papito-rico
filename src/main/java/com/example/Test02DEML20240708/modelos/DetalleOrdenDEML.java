package com.example.Test02DEML20240708.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "detalleOrdenes")
public class DetalleOrdenDEML {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private OrdenDEML ordenDEML;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private ProductoDEML productoDEML;

    @NotNull(message = "La cantidad es requerida")
    @Positive(message = "La cantidad no puede ser de 0")
    private int cantidadDEML;

    @NotNull(message = "El precio es requerido")
    @Positive(message = "El precio no puede ser de 0")
    private double precioDEML;

    public DetalleOrdenDEML() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdenDEML getOrdenDEML() {
        return ordenDEML;
    }

    public void setOrdenDEML(OrdenDEML ordenDEML) {
        this.ordenDEML = ordenDEML;
    }

    public ProductoDEML getProductoDEML() {
        return productoDEML;
    }

    public void setProductoDEML(ProductoDEML productoDEML) {
        this.productoDEML = productoDEML;
    }

    public int getCantidadDEML() {
        return cantidadDEML;
    }

    public void setCantidadDEML(int cantidadDEML) {
        this.cantidadDEML = cantidadDEML;
    }

    public double getPrecioDEML() {
        return precioDEML;
    }

    public void setPrecioDEML(double precioDEML) {
        this.precioDEML = precioDEML;
    }

}
