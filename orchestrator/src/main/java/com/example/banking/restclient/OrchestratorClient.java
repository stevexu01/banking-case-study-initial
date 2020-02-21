package com.example.banking.restclient;

import com.example.banking.model.AccountSummary;
import com.example.banking.model.AutoLoan;
import com.example.banking.model.CreditCard;
import com.example.banking.model.DepositAccount;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        AutoLoanCollection collection = this.restTemplate.getForObject(URLs[0] + clientId, AutoLoanCollection.class);
        return collection.getAutoLoans();
    }

    private List<CreditCard> getCreditCardsByClientId(String clientId) {
        CreditCardCollection collection = this.restTemplate.getForObject(URLs[1] + clientId, CreditCardCollection.class);
        return collection.getCreditCards();
    }

    private List<DepositAccount> getDepositAccountsByClientId(String clientId) {
        DepositAccountCollection collection = this.restTemplate.getForObject(URLs[2] + clientId, DepositAccountCollection.class);
        return collection.getDepositAccounts();
    }

    public AccountSummary getAccountSummaryByClientId(String clientId){
        return new AccountSummary(getAutoLoansByClientId(clientId), getCreditCardsByClientId(clientId), getDepositAccountsByClientId(clientId));
    }


    //Collection types
    private static class AutoLoanCollection {
        List<AutoLoan> autoLoans;

        public AutoLoanCollection(List<AutoLoan> autoLoans) {
            this.autoLoans = autoLoans;
        }

        public List<AutoLoan> getAutoLoans() {
            return autoLoans;
        }
    }

    private static class CreditCardCollection {
        List<CreditCard> creditCards;

        public CreditCardCollection(List<CreditCard> creditCards) {
            this.creditCards = creditCards;
        }

        public List<CreditCard> getCreditCards() {
            return creditCards;
        }
    }

    private static class DepositAccountCollection {
        List<DepositAccount> depositAccounts;

        public DepositAccountCollection(List<DepositAccount> depositAccounts) {
            this.depositAccounts = depositAccounts;
        }

        public List<DepositAccount> getDepositAccounts() {
            return depositAccounts;
        }
    }
}
