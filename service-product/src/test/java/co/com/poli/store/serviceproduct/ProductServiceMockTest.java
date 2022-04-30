package co.com.poli.store.serviceproduct;

import co.com.poli.store.serviceproduct.persistence.entity.Category;
import co.com.poli.store.serviceproduct.persistence.entity.Product;
import co.com.poli.store.serviceproduct.persistence.repository.ProductRepository;
import co.com.poli.store.serviceproduct.service.ProductService;
import co.com.poli.store.serviceproduct.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void begin() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Product product = Product.builder()
                .id(4L)
                .name("balon")
                .price(10000D)
                .stock(10D)
                .category(Category.builder().id(1L).build()).build();
        Mockito.when(productRepository.findById(4L))
                .thenReturn(Optional.of(product));
    }

    @Test
    public void when_findById_return_Product() {
        Product product = productService.findById(4L);
        Assertions.assertThat(product.getName()).isEqualTo("balon");
    }

}
