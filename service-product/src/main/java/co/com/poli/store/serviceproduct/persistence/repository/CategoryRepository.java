package co.com.poli.store.serviceproduct.persistence.repository;

import co.com.poli.store.serviceproduct.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
