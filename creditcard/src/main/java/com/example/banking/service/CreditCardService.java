package com.example.banking.service;

import com.example.banking.model.CreditCard;
import com.example.banking.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardService {

    private CreditCardRepository repository;

    public CreditCardService(CreditCardRepository repository) {
        this.repository = repository;
    }

    public CreditCard createCreditCard(CreditCard creditCard) {
        return this.repository.save(creditCard);
    }

    public List<CreditCard> getCreditCardsByClientId(String clientId) {
        return this.repository.findByClientId(clientId);
    }

    public List<CreditCard> getAllCreditCards() {

        List<CreditCard> allCreditCards = new ArrayList<CreditCard>();

        Iterable iterable = this.repository.findAll();

        iterable.forEach((credcard)->allCreditCards.add((CreditCard) credcard));

        return allCreditCards;
    }

    public CreditCard updateCreditCard(Long id, CreditCard creditCard) {
        CreditCard creditCardFound = null;

        Optional<CreditCard> creditCardOption = this.repository.findById(id);
        if(creditCardOption.isPresent()){
            creditCardFound = this.repository.save(creditCardOption.get());
        } else {
            //TODO: fix not
        }

        creditCardFound.setNumber(creditCard.getNumber());
        creditCardFound.setClientId(creditCard.getClientId());
        creditCardFound.setName(creditCard.getName());

        this.repository.save(creditCardFound);


        return creditCardFound;
    }

    public void deleteCreditCard(Long id) {
        this.repository.deleteById(id);
    }
}
