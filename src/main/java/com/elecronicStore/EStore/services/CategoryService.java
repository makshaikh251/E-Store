package com.elecronicStore.EStore.services;

import com.elecronicStore.EStore.dtos.CategoryDto;
import com.elecronicStore.EStore.dtos.PageableResponse;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto,String categoryId);

    void delete(String categoryId);

    PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    CategoryDto get(String categoryId);
}
