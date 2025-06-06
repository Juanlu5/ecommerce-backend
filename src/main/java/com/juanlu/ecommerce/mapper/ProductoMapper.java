package com.juanlu.ecommerce.mapper;

import com.juanlu.ecommerce.dto.ProductoCreateDTO;
import com.juanlu.ecommerce.dto.ProductoDTO;
import com.juanlu.ecommerce.model.Producto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDTO toDTO(Producto producto);
    Producto toEntity(ProductoCreateDTO dto);
    List<ProductoDTO> toDTOList(List<Producto> productos);
}
