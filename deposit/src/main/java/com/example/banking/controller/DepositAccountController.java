package com.example.banking.controller;


import com.example.banking.model.DepositAccount;
import com.example.banking.service.DepositAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("deposit")
public class DepositAccountController {
    private static final String DEFAULT_MESSAGE_NO_ACCOUNT = "No accounts available to show currently.";

    private static final String RECORD_NOT_DELETED = "Record not deleted.";
    private static final String RECORD_DELETED = "Record deleted.";

    private DepositAccountService service;

    public DepositAccountController(DepositAccountService service) {
        this.service = service;
    }

    /**
     *
     * @param depositAccount
     * @return
     */
    @ResponseBody
    @PostMapping("createDepositAccount")
    public DepositAccount createDepositAccount(@RequestBody DepositAccount depositAccount) {

        DepositAccount  depositAccountCreated = this.service.createDepositAccount(depositAccount);

        //TODO: delete after test
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Created: " + mapper.writeValueAsString(depositAccountCreated));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return depositAccountCreated;
    }

    /**
     *
     * @param clientId
     * @return
     */
    @GetMapping(value = "getDepositAccountsByClientId/{clientId}", produces = "application/json")
    public List<DepositAccount> getDepositAccountsByClientId(@PathVariable("clientId") String clientId) {

        List<DepositAccount> depositAccounts = null;

        try {
            depositAccounts = this.service.getDepositAccountsByClientId(clientId);

        } catch (Exception exc) {
            //TODO: return with default message
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AutoLoan with clientId [" + clientId + "] Not Found", exc);
        }

        if(depositAccounts.isEmpty()) {
            //TODO:return default message
            //Debug only
            System.out.println("Credit card with clientId [" + clientId + "] Not Found");
            DepositAccount defaultAccount = new DepositAccount();
            defaultAccount.setDefaultMessage(DEFAULT_MESSAGE_NO_ACCOUNT);
            depositAccounts.add(defaultAccount);
        }

        return depositAccounts;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "getAllDepositAccounts", produces = "application/json")
    public List<DepositAccount> getAllDepositAccounts(){
        return this.service.getAllDepositAccounts();
    }

    /**
     *
     * @param id
     * @param depositAccount
     * @return
     */
    @PatchMapping(value = "updateDepositAccount/{id}", produces = "application/json")
    @ResponseBody
    public DepositAccount updateDepositAccount(@PathVariable("id") Long id, @RequestBody DepositAccount depositAccount){
        DepositAccount updated = this.service.updateDepositAccount(id, depositAccount);

        return updated;
    }

    @RequestMapping (value = "deleteDepositAccount/{id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public ResponseEntity<String> deleteDepositAccount(@PathVariable("id") Long id){

        try {
            this.service.deleteDepositAccount(id);
            return new ResponseEntity<>(RECORD_DELETED, HttpStatus.NO_CONTENT);
        } catch (Exception exc) {
            return new ResponseEntity<>(RECORD_NOT_DELETED, HttpStatus.NOT_FOUND);
        }
    }
}
