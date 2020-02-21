package com.example.banking.restclient;

import com.example.banking.model.AccountSummary;
import com.example.banking.model.AutoLoan;
import com.example.banking.model.CreditCard;
import com.example.banking.model.DepositAccount;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class OrchestratorClient {

    private static final String[] URLs = {
            "http://localhost:9091/autoloan/getLoansByClientId/",
            "http://localhost:9092/creditcard/getCreditCardsByClientId/",
            "http://localhost:9093/deposit/getDepositAccountsByClientId/"};


    private RestTemplate restTemplate;


    public OrchestratorClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    private List<AutoLoan> getAutoLoansByClientId(String clientId) {
        ResponseEntity<AutoLoan[]> response =
                restTemplate.getForEntity(
                        URLs[0] + clientId,
                        AutoLoan[].class);
        AutoLoan[] loans = response.getBody();

        return Arrays.asList(loans);

    }

    private List<CreditCard> getCreditCardsByClientId(String clientId) {

        ResponseEntity<CreditCard[]> response =
                restTemplate.getForEntity(
                        URLs[1] + clientId,
                        CreditCard[].class);
        CreditCard[] creditCards = response.getBody();

        return Arrays.asList(creditCards);

    }

    private List<DepositAccount> getDepositAccountsByClientId(String clientId) {

        ResponseEntity<DepositAccount[]> response =
                restTemplate.getForEntity(
                        URLs[2] + clientId,
                        DepositAccount[].class);
        DepositAccount[] depositAccounts = response.getBody();

        return Arrays.asList(depositAccounts);

    }

    public AccountSummary getAccountSummaryByClientId(String clientId){
        return new AccountSummary(getAutoLoansByClientId(clientId), getCreditCardsByClientId(clientId), getDepositAccountsByClientId(clientId));
    }


    //Collection types
    @JsonIdentityReference
    public static class AutoLoanCollection {
        List<AutoLoan> autoLoans;

        public AutoLoanCollection(List<AutoLoan> autoLoans) {
            this.autoLoans = autoLoans;
        }

        public List<AutoLoan> getAutoLoans() {
            return autoLoans;
        }
    }

    public static class CreditCardCollection {
        List<CreditCard> creditCards;

        public CreditCardCollection(List<CreditCard> creditCards) {
            this.creditCards = creditCards;
        }

        public List<CreditCard> getCreditCards() {
            return creditCards;
        }
    }

    public static class DepositAccountCollection {
        List<DepositAccount> depositAccounts;

        public DepositAccountCollection(List<DepositAccount> depositAccounts) {
            this.depositAccounts = depositAccounts;
        }

        public List<DepositAccount> getDepositAccounts() {
            return depositAccounts;
        }
    }
}
