package com.juanlu.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import com.juanlu.ecommerce.model.Producto;
import com.juanlu.ecommerce.service.ProductoService;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductoService productoService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    void crearProducto_deberiaDevolvelr201YProductoDTO()throws Exception{
        ProductoCreateDTO createDTO = new ProductoCreateDTO();
        createDTO.setNombre("Mockeado");
        createDTO.setDescripcion("Probando POST");
        createDTO.setPrecio(15.0);
        createDTO.setStock(8);
        
        ProductoDTO responseDTO = new ProductoDTO();
        responseDTO.setId(1L);
        responseDTO.setNombre("Mockeado");
        responseDTO.setDescripcion("Probando POST");
        responseDTO.setPrecio(15.0);
        responseDTO.setStock(8);
        
        Mockito.when(productoService.crearProducto(any())).thenReturn(responseDTO);
        
        mockMvc.perform(post("/productos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Mockeado"));
    }
    
    @Test
    void obtenerProductoPorId_existente_deberiaDevolverDTO() throws Exception{
        Long id = 1L;
        
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);
        productoDTO.setNombre("Camiseta Test");
        productoDTO.setDescripcion("Camiseta para pruebas");
        productoDTO.setPrecio(20.0);
        productoDTO.setStock(12);
        
        Mockito.when(productoService.obtenerPorId(id)).thenReturn(productoDTO);
        
        mockMvc.perform(get("/productos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nombre").value("Camiseta Test"))
                .andExpect(jsonPath("$.precio").value(20.0))
                .andExpect(jsonPath("$.stock").value(12));
    }
    
    @Test
    void actualizarProducto_existente_deberiaDevolverDTOActualizado() throws Exception{
        Long id = 1L;
        
        ProductoCreateDTO updateDTO = new ProductoCreateDTO();
        updateDTO.setNombre("Producto actualizado");
        updateDTO.setDescripcion("Descripción actualizada");
        updateDTO.setPrecio(35.0);
        updateDTO.setStock(20);
        
        ProductoDTO productoActualizado = new ProductoDTO();
        productoActualizado.setId(id);
        productoActualizado.setNombre(updateDTO.getNombre());
        productoActualizado.setDescripcion(updateDTO.getDescripcion());
        productoActualizado.setPrecio(updateDTO.getPrecio());
        productoActualizado.setStock(updateDTO.getStock());
        
        Mockito.when(productoService.actualizarProducto(eq(id), any())).thenReturn(productoActualizado);
        
        mockMvc.perform(put("/productos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.nombre").value("Producto actualizado"))
                .andExpect(jsonPath("$.precio").value(35.0))
                .andExpect(jsonPath("$.stock").value(20));
    }
    @Test
    void eliminarProducto_existente_deberiaDevolverNoContent()throws Exception{
        Long id = 1L;
        
        Mockito.doNothing().when(productoService).eliminarProducto(id);
        
        mockMvc.perform(delete("/productos/{id}",id))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void eliminarProducto_noExistente_deberiaDevolverNotFound()throws Exception{
        Long id = 999L;
        
        Mockito.doThrow(new RuntimeException("Producto no encontrado"))
                .when(productoService).eliminarProducto(id);
        
        mockMvc.perform(delete("/productos/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Producto no encontrado"))
                .andExpect(jsonPath("$.error").value("Not Found"));
    }
}
