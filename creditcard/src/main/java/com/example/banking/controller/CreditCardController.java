package com.example.banking.controller;

import com.example.banking.model.CreditCard;
import com.example.banking.service.CreditCardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("creditcard")
public class CreditCardController {
    private static final String DEFAULT_MESSAGE_NO_ACCOUNT = "No accounts available to show currently.";

    private static final String RECORD_NOT_DELETED = "Record not deleted.";
    private static final String RECORD_DELETED = "Record deleted.";

    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     *
     * @param creditCard
     * @return
     */
    @ResponseBody
    @PostMapping("createCreditCard")
    public CreditCard createCreditCard(@RequestBody CreditCard creditCard) {

        final CreditCard  creditCardCreated = this.creditCardService.createCreditCard(creditCard);

        //TODO: delete after test
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Created: " + mapper.writeValueAsString(creditCardCreated));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return creditCardCreated;
    }

    /**
     *
     * @param clientId
     * @return
     */
    @GetMapping(value = "getCreditCardsByClientId/{clientId}", produces = "application/json")
    public List<CreditCard> getCreditCardsByClientId(@PathVariable("clientId") String clientId) {

        List<CreditCard> creditCardList = null;

        try {
            creditCardList = this.creditCardService.getCreditCardsByClientId(clientId);

        } catch (Exception exc) {
            //TODO: return with default message
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AutoLoan with clientId [" + clientId + "] Not Found", exc);
        }

        if(creditCardList.isEmpty()) {
            //TODO:return default message
            //Debug only
            System.out.println("Credit card with clientId [" + clientId + "] Not Found");
            CreditCard defaultLoan = new CreditCard();
            defaultLoan.setDefaultMessage(DEFAULT_MESSAGE_NO_ACCOUNT);
            creditCardList.add(defaultLoan);
        }

        return creditCardList;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "getAllCreditCards", produces = "application/json")
    public List<CreditCard> getAllCreditCards(){
        return this.creditCardService.getAllCreditCards();
    }

    /**
     *
     * @param id
     * @param creditCard
     * @return
     */
    @PatchMapping(value = "updateCreditCard/{id}", produces = "application/json")
    @ResponseBody
    public CreditCard updateCreditCard(@PathVariable("id") Long id, @RequestBody CreditCard creditCard){
        CreditCard updated = this.creditCardService.updateCreditCard(id, creditCard);

        return updated;
    }

    @RequestMapping (value = "deleteCreditCard/{id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity<String> deleteCreditCard(@PathVariable("id") Long id){

        try {
            this.creditCardService.deleteCreditCard(id);
            return new ResponseEntity<>(RECORD_DELETED, HttpStatus.NO_CONTENT);
        } catch (Exception exc) {
            return new ResponseEntity<>(RECORD_NOT_DELETED, HttpStatus.NOT_FOUND);
        }
    }
}
