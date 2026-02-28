package com.devlog.core.repository.category;

import com.devlog.core.common.enumulation.UseYN;
import com.devlog.core.entity.category.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUseYnIs(UseYN useYn);
}
