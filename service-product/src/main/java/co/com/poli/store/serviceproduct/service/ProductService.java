package co.com.poli.store.serviceproduct.service;

import co.com.poli.store.serviceproduct.persistence.entity.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);

    void delete(Product product);

    List<Product> findAll();

    Product findById(Long id);

}
