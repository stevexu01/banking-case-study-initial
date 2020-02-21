package com.example.banking.controller;

import com.example.banking.model.AutoLoan;
import com.example.banking.service.AutoLoanService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("autoloan")
public class AutoLoanController {

    private static final String DEFAULT_MESSAGE_NO_ACCOUNT = "No accounts available to show currently";

   private AutoLoanService autoLoanService;

    public AutoLoanController(AutoLoanService autoLoanService) {
        this.autoLoanService = autoLoanService;
    }

    /**
     *
     * @param autoLoan
     * @return
     */
    @ResponseBody
    @PostMapping("createLoan")
    public AutoLoan createAutoLoan(@RequestBody AutoLoan autoLoan) {

       final AutoLoan  autoLoanCreated = this.autoLoanService.createAutoLoan(autoLoan);

       ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println("Created: " + mapper.writeValueAsString(autoLoanCreated));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return autoLoanCreated;
    }

    /**
     *
     * @param clientId
     * @return
     */
    @GetMapping(value = "getLoansByClientId/{clientId}", produces = "application/json")
    public List<AutoLoan> getLoansByClientId(@PathVariable("clientId") String clientId) {

        List<AutoLoan> autoLoanList = null;

        try {
            autoLoanList = this.autoLoanService.getLoansByClientId(clientId);

        } catch (Exception exc) {
            //TODO: return with default message
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AutoLoan with clientId [" + clientId + "] Not Found", exc);
        }

        if(autoLoanList.isEmpty()) {
            //TODO:return default message
            //Debug only
            System.out.println("AutoLoan with clientId [" + clientId + "] Not Found");
            AutoLoan defaultLoan = new AutoLoan();
            defaultLoan.setDefaultMessage(DEFAULT_MESSAGE_NO_ACCOUNT);
            autoLoanList.add(defaultLoan);
        }

        return autoLoanList;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "getAllLoans", produces = "application/json")
    public List<AutoLoan> getAllLoans(){
        return this.autoLoanService.getAllLoans();
    }

    /**
     *
     * @param id
     * @param autoLoan
     * @return
     */
    @PatchMapping(value = "updateLoan/{id}", produces = "application/json")
    @ResponseBody
    public AutoLoan updateLoan(@PathVariable("id") Long id, @RequestBody AutoLoan autoLoan){
        AutoLoan updated = this.autoLoanService.updateLoan(id, autoLoan);

        return updated;
    }

    @RequestMapping (value = "deleteLoan/{id}", method = {RequestMethod.DELETE}, produces = "application/json")
    public void deleteLoan(@PathVariable("id") Long id){
        this.autoLoanService.deleteLoan(id);
        //TODO: failed?
    }

}
