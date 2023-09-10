package com.elecronicStore.EStore.repositories;

import com.elecronicStore.EStore.dtos.CategoryDto;
import com.elecronicStore.EStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
