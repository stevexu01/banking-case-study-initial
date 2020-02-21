package com.example.banking.service;

import com.example.banking.model.AccountSummary;
import com.example.banking.model.AutoLoan;
import com.example.banking.model.CreditCard;
import com.example.banking.model.DepositAccount;
import com.example.banking.restclient.OrchestratorClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrchestratorService {
    private OrchestratorClient client;

    public OrchestratorService(OrchestratorClient client) {
        this.client = client;
    }


    public AccountSummary getAccountSummaryByClientId(String clientId) {
        return client.getAccountSummaryByClientId(clientId);
    }
}
