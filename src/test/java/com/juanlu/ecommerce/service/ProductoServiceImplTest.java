package com.juanlu.ecommerce.service;

import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import com.juanlu.ecommerce.mapper.ProductoMapper;
import com.juanlu.ecommerce.model.Producto;
import com.juanlu.ecommerce.repository.ProductoRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class ProductoServiceImplTest {
    @InjectMocks
    private ProductoServiceImpl productoService;
    
    @Mock
    private ProductoRepository productoRepository;
    
    @Mock
    private ProductoMapper productoMapper;
    
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void createProducto_deberiaGuardarYDevolverDTO(){
       ProductoCreateDTO dto= new ProductoCreateDTO();
       dto.setNombre("Test");
       dto.setDescripcion("Producto de test");
       dto.setPrecio(10.0);
       dto.setStock(5);
       
       Producto producto = new Producto();
       Producto productoGuardado = new Producto();
       productoGuardado.setId(1L);
       
       ProductoDTO productoDTO = new ProductoDTO();
       productoDTO.setId(1L);
       
       when(productoMapper.toEntity(dto)).thenReturn(producto);
       when(productoRepository.save(producto)).thenReturn(productoGuardado);
       when(productoMapper.toDTO(productoGuardado)).thenReturn(productoDTO);
       
       ProductoDTO resultado = productoService.crearProducto(dto);
       
       assertNotNull(resultado);
       assertEquals(1L, resultado.getId());
    }
    
    @Test
    void obtenerPorId_productoExistente(){
        Producto producto = new Producto();
        producto.setId(1L);
        ProductoDTO dto = new ProductoDTO();
        dto.setId(1L);
        
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoMapper.toDTO(producto)).thenReturn(dto);
        
        ProductoDTO resultado = productoService.obtenerPorId(1L);
        assertEquals(1L, resultado.getId());
    }
    
    @Test
    void obtenerPorId_productoNoExiste_lanzaExcepcion(){
        when (productoRepository.findById(999L)).thenReturn(Optional.empty());
        
        RuntimeException ex = assertThrows(RuntimeException.class,()->
            productoService.obtenerPorId(999L));
        
        assertEquals("Producto no encontrado", ex.getMessage());
    }
    
    @Test
    void eliminarProducto_existente_noLanzaExepcion(){
        when(productoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productoRepository).deleteById(1L);
        
        assertDoesNotThrow(()->productoService.eliminarProducto(1L));
    }
    
    @Test
    void actualizarProducto_productoExistente_deberiaActualizarYDevolverDTO(){
        
        Long id = 1L;
        
        ProductoCreateDTO dto = new ProductoCreateDTO();
        dto.setNombre("Nuevo nombre");
        dto.setDescripcion("Nueva descripción");
        dto.setPrecio(25.0);
        dto.setStock(15);
        
        Producto productoExistente = new Producto();
        productoExistente.setId(id);
        productoExistente.setNombre("Viejo nombre");
        productoExistente.setDescripcion("Vieja descripción");
        productoExistente.setPrecio(10.0);
        productoExistente.setStock(5);
        
        Producto productoActualizado = new Producto();
        productoActualizado.setId(id);
        productoActualizado.setNombre(dto.getNombre());
        productoActualizado.setDescripcion(dto.getDescripcion());
        productoActualizado.setPrecio(dto.getPrecio());
        productoActualizado.setStock(dto.getStock());
        
        ProductoDTO resultadoEsperado = new ProductoDTO();
        
        resultadoEsperado.setId(id);
        resultadoEsperado.setNombre(dto.getNombre());
        resultadoEsperado.setDescripcion(dto.getDescripcion());
        resultadoEsperado.setPrecio(dto.getPrecio());
        resultadoEsperado.setStock(dto.getStock());
        
        when(productoRepository.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(productoExistente)).thenReturn(productoActualizado);
        when(productoMapper.toDTO(productoActualizado)).thenReturn(resultadoEsperado);
        
        ProductoDTO resultado = productoService.actualizarProducto(id, dto);
        
        assertNotNull(resultado);
        assertEquals("Nuevo nombre", resultado.getNombre());
        assertEquals(25.0, resultado.getPrecio());
        assertEquals(15, resultado.getStock());
    }
}
