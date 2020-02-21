package com.example.banking.model;

public class AccountSummaryWithHead {
    private AccountSummary accountSummary;

    public AccountSummaryWithHead(AccountSummary accountSummary) {
        this.accountSummary = accountSummary;
    }

    public AccountSummary getAccountSummary() {
        return accountSummary;
    }

    public void setAccountSummary(AccountSummary accountSummary) {
        this.accountSummary = accountSummary;
    }
}
