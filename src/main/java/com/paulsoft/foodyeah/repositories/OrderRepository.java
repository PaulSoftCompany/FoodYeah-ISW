package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    //TODO
}
