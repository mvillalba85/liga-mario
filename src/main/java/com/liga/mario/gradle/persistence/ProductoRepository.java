package com.liga.mario.gradle.persistence;

import com.liga.mario.gradle.domain.Product;
import com.liga.mario.gradle.domain.repository.ProductRepository;
import com.liga.mario.gradle.persistence.crud.ProductoCrudReposity;
import com.liga.mario.gradle.persistence.entity.Producto;
import com.liga.mario.gradle.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudReposity productoCrudReposity;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudReposity.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudReposity.getByCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getByCategoriaOrderByNombre(int categoryId) {
        List<Producto> productos = productoCrudReposity.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudReposity.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudReposity.findById(productId).map(produto -> mapper.toProduct(produto));
    }

    @Override
    public Optional<Product> getByNombre(String name) {
        Producto producto = productoCrudReposity.findByNombre(name);
        return Optional.of(mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudReposity.save(producto));
    }

    @Override
    public void delete(int idProducto){
        productoCrudReposity.deleteById(idProducto);
    }
}
