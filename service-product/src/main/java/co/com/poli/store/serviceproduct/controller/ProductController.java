package co.com.poli.store.serviceproduct.controller;

import co.com.poli.store.serviceproduct.helpers.Response;
import co.com.poli.store.serviceproduct.helpers.ResponseBuilder;
import co.com.poli.store.serviceproduct.persistence.entity.Product;
import co.com.poli.store.serviceproduct.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ResponseBuilder builder;

    @PostMapping
    public Response save(@RequestBody Product product) {
        productService.save(product);
        return builder.success(product);
    }

    @DeleteMapping("/{id}")
    public Product delete(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        productService.delete(product);
        return product;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }

}
