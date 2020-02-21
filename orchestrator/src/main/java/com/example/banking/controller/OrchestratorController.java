package com.example.banking.controller;

import com.example.banking.model.AccountSummary;
import com.example.banking.model.AutoLoan;
import com.example.banking.model.CreditCard;
import com.example.banking.model.DepositAccount;
import com.example.banking.restclient.OrchestratorClient;
import com.example.banking.service.OrchestratorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController

public class OrchestratorController {
    private OrchestratorService service;

    public OrchestratorController(OrchestratorService service) {
        this.service = service;
    }

    @GetMapping("getAccountSummaryByClientId/{clientId}")
    public AccountSummary getAccountSummaryByClientId(@PathVariable("clientId") String clientId){
        return service.getAccountSummaryByClientId(clientId);

    }
}
