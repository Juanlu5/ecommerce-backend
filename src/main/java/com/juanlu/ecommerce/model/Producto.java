package com.juanlu.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos")

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    
    @Size(max = 255, message = "la descripción no puede superara los 255 caracteres")
    private String descripcion;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor de 0.0")
    private double precio;
    
    @Min(value = 0, message = "El stock debe ser mayor de 0")
    private Integer stock;
    
    public Producto(){}
    
    public Producto(String nombre, String descripcion, double precio, Integer stock){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}
