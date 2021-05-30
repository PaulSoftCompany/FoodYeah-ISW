package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    //TODO

}
