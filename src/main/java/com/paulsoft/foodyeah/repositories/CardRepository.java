package com.paulsoft.foodyeah.repositories;

import com.paulsoft.foodyeah.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    //TODO
    List<Card> findAllByCustomerId(Long id);
    Optional<Card> findCardByCardNumber(Long number);
}
