package com.paulsoft.foodyeah.services;

import com.paulsoft.foodyeah.dtos.CardDto.CardDto;
import com.paulsoft.foodyeah.dtos.CardDto.CreateCardDto;
import com.paulsoft.foodyeah.dtos.CardDto.UpdateCardDto;
import com.paulsoft.foodyeah.entities.Card;
import com.paulsoft.foodyeah.exceptions.ResourceException;

import java.util.List;

public interface CardService {
    List<CardDto> getCards() throws ResourceException;

    CardDto getCardById(Long id) throws ResourceException;

    List<CardDto> getAllByCustomerId(long id) throws ResourceException;

    CardDto createCard(CreateCardDto createCardDto) throws ResourceException;

    String deleteCard(Long id) throws ResourceException;
}
