package co.com.poli.store.serviceproduct.controller;

import co.com.poli.store.serviceproduct.helpers.ErrorMessage;
import co.com.poli.store.serviceproduct.helpers.Response;
import co.com.poli.store.serviceproduct.helpers.ResponseBuild;
import co.com.poli.store.serviceproduct.persistence.entity.Product;
import co.com.poli.store.serviceproduct.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@Valid @RequestBody Product product, BindingResult result) {
        if (result.hasErrors()) {
            return builder.failed(formatMessage(result));
        }
        productService.save(product);
        return builder.success(product);
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        productService.delete(product);
        return builder.success(product);
    }

    @GetMapping
    public Response findAll() {
        return builder.success(productService.findAll());
    }

    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id) {
        return builder.success(productService.findById(id));
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .error(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}

/*
* {
  "code": 500,
  "data": [
    {
      "price": "El precio debe ser mayor que cero"
    },
    {
      "name": "El nombre no debe ser vacio"
    }
  ]
}*/