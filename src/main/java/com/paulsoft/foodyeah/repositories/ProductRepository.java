package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);
    List<Product> findAllBySellDay(byte sellDay);
    List<Product> findAllByState(Boolean State);
    List<Product> findAllByProductCategoryId(Long categoryId);
}
