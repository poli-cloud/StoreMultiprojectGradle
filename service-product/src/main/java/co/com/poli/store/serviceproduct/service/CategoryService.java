package co.com.poli.store.serviceproduct.service;

import co.com.poli.store.serviceproduct.persistence.entity.Category;
import co.com.poli.store.serviceproduct.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public void save(Category category) {
        repository.save(category);
    }

}
