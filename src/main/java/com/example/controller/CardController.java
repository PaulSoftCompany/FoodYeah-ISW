
package com.example.controller;


import com.example.entity.Card;
import com.example.service.CardService;
import com.example.util.Message;
import lombok.extern.slf4j.Slf4j;
//import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
     private CardService cardService;

    // -------------------Retrieve All Cards--------------------------------------------

    @GetMapping
    public   ResponseEntity <List<Card>> listAllCards(@RequestParam(name = "cardId", required = false) Long cardId){
        List<Card> cards = new ArrayList<>();

        if(null==cardId){
                cards = cardService.findCardAll();
                if(cards.isEmpty()){
                    return ResponseEntity.noContent().build();
                }
        }

        return ResponseEntity.ok(cards);

    }


    // -------------------Retrieve Single Card------------------------------------------

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCard(@PathVariable("id") long id){
        log.info("Fetching Card with Id {}", id);

        Card card = cardService.getCard(id);

        if( null == card){
            log.error("Card with id {} not found.",id);
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(card);

    }
    //------------------Retrieve All By Customer Id------------------
    @GetMapping("/customers={id}")
    public ResponseEntity<List<Card>> getAllByCustomerId(@PathVariable(name="id") long id)
    {
        List<Card> cards;
        cards = cardService.getAllByCustomerId(id);

        return ResponseEntity.ok(cards);

    }

     // -------------------Create a Card-------------------------------------------

    @PostMapping
    public ResponseEntity<Card> createCard(@Valid @RequestBody Card card, BindingResult result){
        log.info("Creating Card : {}", card);
        if ( result.hasErrors()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.formatMessage(result));
        }
        Card cardDB = cardService.createCard(card);
       return  ResponseEntity.status(HttpStatus.CREATED).body(cardDB);
    }


    // ------------------- Update a Card ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable("id") long id, @RequestBody Card card){
        log.info("Updating Card with id {}",id);
        Card currentCard = cardService.getCard(id);

        if(null == currentCard){
            log.error("Unable to update Card with id {} not founded",id);
            return ResponseEntity.notFound().build();
        }

        card.setId(id);
        currentCard = cardService.updateCard(card);
        return ResponseEntity.ok(currentCard);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Card> deleteCard(@PathVariable("id")long id){
        log.info("Fetching & Deleting Card with id {}", id);
        Card card = cardService.getCard(id);
        if(null == card){
            log.error("Unable to delete. Card with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        card = cardService.deleteCard(id);
        return ResponseEntity.ok(card);
    }
}
