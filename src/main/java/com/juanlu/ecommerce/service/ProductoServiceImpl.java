package com.juanlu.ecommerce.service;

import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import com.juanlu.ecommerce.mapper.ProductoMapper;
import com.juanlu.ecommerce.model.Producto;
import com.juanlu.ecommerce.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ProductoMapper mapper;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return mapper.toDTOList(productoRepository.findAll());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id).map(mapper::toDTO).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public ProductoDTO crearProducto(ProductoCreateDTO dto) {
        Producto producto = mapper.toEntity(dto);
        return mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoCreateDTO dto) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    
}
