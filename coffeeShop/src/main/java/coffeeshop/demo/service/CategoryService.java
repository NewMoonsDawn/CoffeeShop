package coffeeshop.demo.service;

import coffeeshop.demo.model.entity.Category;
import coffeeshop.demo.model.entity.CategoryName;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryName category);
}
