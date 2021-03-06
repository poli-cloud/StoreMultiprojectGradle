package co.com.poli.store.serviceproduct.service;

import co.com.poli.store.serviceproduct.persistence.entity.Product;
import co.com.poli.store.serviceproduct.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
