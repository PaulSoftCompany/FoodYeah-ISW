package com.example.service;

import com.example.entity.Card;

import java.util.List;

public interface CardService {
    List<Card> findCardAll();
    Card getCard(Long id);

    List<Card> getAllByCustomerId(long Id);

    Card createCard(Card card);
    Card updateCard(Card card);
    Card deleteCard(Long id);

}
