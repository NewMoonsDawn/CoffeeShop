package coffeeshop.demo.repository;

import coffeeshop.demo.model.entity.Category;
import coffeeshop.demo.model.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(CategoryName name);
}
