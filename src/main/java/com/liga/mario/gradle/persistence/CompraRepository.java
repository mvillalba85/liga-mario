package com.liga.mario.gradle.persistence;

import com.liga.mario.gradle.domain.Purchase;
import com.liga.mario.gradle.domain.repository.PurchaseRepository;
import com.liga.mario.gradle.persistence.crud.CompraCrudRepository;
import com.liga.mario.gradle.persistence.entity.Compra;
import com.liga.mario.gradle.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return mapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
