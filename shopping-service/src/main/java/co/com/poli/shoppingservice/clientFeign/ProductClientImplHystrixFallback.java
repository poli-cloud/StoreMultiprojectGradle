package co.com.poli.shoppingservice.clientFeign;

import co.com.poli.shoppingservice.helpers.Response;
import co.com.poli.shoppingservice.helpers.ResponseBuild;
import co.com.poli.shoppingservice.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductClientImplHystrixFallback implements ProductClient {

    private final ResponseBuild builder;

    @Override
    public Response findById(Long id) {
        return builder.success(new Product());
    }
}
