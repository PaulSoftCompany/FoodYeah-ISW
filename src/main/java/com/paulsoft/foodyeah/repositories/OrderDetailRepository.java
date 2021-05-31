package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Optional<List<OrderDetail>> findAllByOrderId(Long aLong);
}
