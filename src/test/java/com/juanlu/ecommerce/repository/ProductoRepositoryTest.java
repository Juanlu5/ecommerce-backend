package com.juanlu.ecommerce.repository;

import com.juanlu.ecommerce.model.Producto;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@ActiveProfiles("test")
public class ProductoRepositoryTest {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Test
    void guardarYBuscarProducto(){
        Producto producto = new Producto();
        producto.setNombre("Integración");
        producto.setDescripcion("Producto de integración");
        producto.setPrecio(50.0);
        producto.setStock(10);
        
        Producto guardado = productoRepository.save(producto);
        
        Optional<Producto> encontrado = productoRepository.findById(guardado.getId());
        
        assertTrue(encontrado.isPresent());
        assertEquals("Integración", encontrado.get().getNombre());
    }
}
