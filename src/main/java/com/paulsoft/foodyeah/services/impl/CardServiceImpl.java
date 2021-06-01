package com.paulsoft.foodyeah.services.impl;

import com.paulsoft.foodyeah.dtos.CardDto.CardDto;
import com.paulsoft.foodyeah.dtos.CardDto.CreateCardDto;
import com.paulsoft.foodyeah.dtos.CardDto.UpdateCardDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.CreateCustomerDto;
import com.paulsoft.foodyeah.dtos.CustomerDto.CustomerDto;
import com.paulsoft.foodyeah.entities.Card;
import com.paulsoft.foodyeah.entities.Customer;
import com.paulsoft.foodyeah.exceptions.InternalServerErrorException;
import com.paulsoft.foodyeah.exceptions.NotFoundException;
import com.paulsoft.foodyeah.exceptions.ResourceException;
import com.paulsoft.foodyeah.repositories.CardRepository;
import com.paulsoft.foodyeah.repositories.CustomerRepository;
import com.paulsoft.foodyeah.services.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public static final ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<CardDto> getCards() throws ResourceException{
        return convertToResources(cardRepository.findAll());
    }

    @Override
    public CardDto getCardById(Long id) throws ResourceException{
        return convertToResource(cardRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND")));
    }

    @Override
    public List<CardDto> getAllByCustomerId(long id) throws ResourceException{
        return convertToResources(cardRepository.findAllByCustomerId(id));
    }

    @Override
    public CardDto createCard(CreateCardDto createCardDto) throws ResourceException{
        if(cardRepository.findCardByCardNumber(createCardDto.getCardNumber()).isPresent()){
            throw new InternalServerErrorException("CARD EXISTS", "CARD EXISTS");
        }
        Card card = new Card();
        Customer customer = customerRepository.findById(createCardDto.getCustomerId())
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
        card.setCustomer(customer);
        card.setCardMoney(createCardDto.getCardMoney());
        card.setCardNumber(createCardDto.getCardNumber());
        card.setExpireDate(createCardDto.getExpireDate());
        card.setType(createCardDto.getType());
        card.setOwner(createCardDto.getOwner());
        card.setState(true);
        return convertToResource(cardRepository.save(card));
    }

    @Override
    public String deleteCard(Long id) throws ResourceException{
        Card card = cardRepository.findById(id)
                .orElseThrow(()->new NotFoundException("NOT_FOUND","NOT_FOUND"));
        card.setState(false);
        cardRepository.save(card);
        return card.getCardNumber().toString();
    }

    private Card getCardEntity(Long id) throws ResourceException {
        return cardRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND","NOT_FOUND"));
    }

    private List<CardDto> convertToResources(List<Card> cards) {
        return cards.stream().map(x -> modelMapper.map(x, CardDto.class)).collect(Collectors.toList());
    }
    private Card convertToEntity(CreateCardDto resource){return  modelMapper.map(resource, Card.class);}

    private CardDto convertToResource(Card entity){return  modelMapper.map(entity,CardDto.class);}
}
