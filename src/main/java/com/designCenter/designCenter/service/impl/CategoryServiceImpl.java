package com.designCenter.designCenter.service.impl;

import com.designCenter.designCenter.dto.category.CategoryReqDto;
import com.designCenter.designCenter.dto.common.CustomServiceException;
import com.designCenter.designCenter.entity.Category;
import com.designCenter.designCenter.repository.CategoryRepository;
import com.designCenter.designCenter.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void CreateCategory(CategoryReqDto reqDto) {
        Category category = categoryRepository.findByCategoryName(reqDto.getCategoryName());
        if(category != null) throw new CustomServiceException("Category already Exists..!");
        log.info("Saving new Category-Name:{}",reqDto.getCategoryName());
        category = Category.builder()
                .categoryName(reqDto.getCategoryName())
                .build();
        categoryRepository.save(category);
    }




}
