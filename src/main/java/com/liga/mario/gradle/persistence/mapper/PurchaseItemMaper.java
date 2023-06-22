package com.liga.mario.gradle.persistence.mapper;

import com.liga.mario.gradle.domain.PurchaseItem;
import com.liga.mario.gradle.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMaper {

    @Mappings({
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quantity"),
//            @Mapping(source = "",target = "total"), No se agrega porque tienen el mismo nombre de variable de referencia
            @Mapping(source = "estado",target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto comprasProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    ComprasProducto toComprasProducto(PurchaseItem purchaseItem);
}
