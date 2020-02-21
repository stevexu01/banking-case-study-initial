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

        List<AutoLoan> autoLoan = null;

        try {
            autoLoan = this.autoLoanService.getLoansByClientId(clientId);

        } catch (Exception exc) {
            //TODO: return with default message
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "AutoLoan with clientId [" + clientId + "] Not Found", exc);
        }

        if(null == autoLoan) {
            //TODO:return default message

        }

        return autoLoan;
    }

}
