package co.com.poli.shoppingservice.clientFeign;

import co.com.poli.shoppingservice.helpers.Response;
import co.com.poli.shoppingservice.helpers.ResponseBuild;
import co.com.poli.shoppingservice.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomerClientImplHystrixFallback implements CustomerClient {

    private final ResponseBuild builder;

    @Override
    public Response findByNumberID(String numberID) {
        return builder.success(new Customer());
    }

    @Override
    public Response findByID(Long id) {
        return builder.success(new Customer());
    }
}
