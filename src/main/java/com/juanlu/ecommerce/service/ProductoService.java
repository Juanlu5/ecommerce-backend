package com.juanlu.ecommerce.service;

import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {
    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crearProducto(ProductoCreateDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoCreateDTO productoDTO);
    void eliminarProducto(Long id);
}
