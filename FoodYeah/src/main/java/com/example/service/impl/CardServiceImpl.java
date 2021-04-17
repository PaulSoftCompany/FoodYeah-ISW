package com.example.service.impl;

import com.example.entity.Card;
import com.example.entity.Customer;
import com.example.repository.CardRepository;
import com.example.repository.CustomerRepository;
import com.example.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Card> findCardAll() {
       return cardRepository.findAll();
    }

    @Override
    public Card getCard(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> getAllByCustomerId(long Id) {

        return cardRepository.findAllByCustomerId(Id);
    }


    @Override
    public Card createCard(Card card) {

        Customer customer = customerRepository.getOne(card.getCustomer().getId());
        card.setState("CREATED");
        card.setCardOwnerName(customer.getCustomerName());
        return cardRepository.save(card);

    }

    @Override
    public Card updateCard(Card card) {
        Card cardDB=this.getCard(card.getId());
        if(cardDB==null){
            return null;
        }
        Customer customer = customerRepository.getOne(card.getCustomer().getId());
        cardDB.setCustomer(card.getCustomer());
        cardDB.setCardNumber(card.getCardNumber());
        cardDB.setCardCvi(card.getCardCvi());
        cardDB.setCardOwnerName(customer.getCustomerName());
        cardDB.setCardExpireDate(card.getCardExpireDate());
        cardDB.setCardMoney(card.getCardMoney());
        cardDB.setState("UPDATED");
        return cardRepository.save(cardDB);
    }

    @Override
    public Card deleteCard(Long id) {
        Card cardDB=this.getCard(id);
        if(cardDB==null){
            return null;
        }
        cardDB.setState("DELETED");
        return cardRepository.save(cardDB);
    }
}
