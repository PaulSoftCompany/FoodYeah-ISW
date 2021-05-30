package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {
    //TODO
    Optional<ProductCategory> findByName(String name);
    Optional<List<ProductCategory>> findAllByState(Boolean state);

}
