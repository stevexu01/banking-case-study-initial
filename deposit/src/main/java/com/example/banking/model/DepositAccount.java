package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
public class DepositAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private String clientId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private String accountNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private Double balance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column
    private Double initialBalance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String defaultMessage;


    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    // Setter Methods

    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public String toString() {
        return "DepositAccount{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", initialBalance=" + initialBalance +
                '}';
    }
}
