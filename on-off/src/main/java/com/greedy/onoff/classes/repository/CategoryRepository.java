package com.greedy.template.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greedy.template.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
