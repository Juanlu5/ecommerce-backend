package com.juanlu.ecommerce.controller;

import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import com.juanlu.ecommerce.model.Producto;
import com.juanlu.ecommerce.repository.ProductoRepository;
import com.juanlu.ecommerce.service.ProductoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public List<ProductoDTO> obtenerTodos(){
        return productoService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
    
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@Valid @RequestBody ProductoCreateDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id,@Valid @RequestBody ProductoCreateDTO dto){
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}