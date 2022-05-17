package co.com.poli.store.serviceproduct.controller;

import co.com.poli.store.serviceproduct.helpers.Response;
import co.com.poli.store.serviceproduct.helpers.ResponseBuild;
import co.com.poli.store.serviceproduct.persistence.entity.Category;
import co.com.poli.store.serviceproduct.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final ResponseBuild builder;

    @PostMapping
    public Response save(@RequestBody Category category) {
        service.save(category);
        return builder.success(category);
    }
}
