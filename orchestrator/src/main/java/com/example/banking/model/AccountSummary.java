package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AccountSummary {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("autoLoanAccounts")
    private List<AutoLoan> autoLoans;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("creditAccounts")
    private List<CreditCard> creditCards;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("depositAccounts")
    private List<DepositAccount> depositAccounts;

    public AccountSummary(List<AutoLoan> autoLoans, List<CreditCard> creditCards, List<DepositAccount> depositAccounts) {
        this.autoLoans = autoLoans;
        this.creditCards = creditCards;
        this.depositAccounts = depositAccounts;
    }
}
