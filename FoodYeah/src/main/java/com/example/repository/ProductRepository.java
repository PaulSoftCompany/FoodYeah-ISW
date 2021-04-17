package com.example.repository;

import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "SELECT * FROM PRODUCTS u WHERE u.product_sellday =?1",nativeQuery = true)
    List<Product> findBySellDay(int SellDay);
    @Query(value = "SELECT * FROM  PRODUCTS where PRODUCTS.CATEGORY_ID = ?1  ",nativeQuery = true)
    List<Product> findByCategoryId(long categoryId);
    @Query(value = "SELECT * FROM PRODUCTS u WHERE u.product_sellday <= 5 and u.category_id = 1 ",nativeQuery = true)
    List<Product>  menuSemanal();
    @Query(value = "SELECT * FROM PRODUCTS u WHERE  u.category_id > 1 ",nativeQuery = true)
    List<Product>  platosALaCarta();
    @Query(value = "SELECT * FROM PRODUCTS u WHERE  u.category_id > 1 AND u.category_id = ?1  ",nativeQuery = true)
    List<Product>  platosALaCartaByCategory(long categoryId);
}
