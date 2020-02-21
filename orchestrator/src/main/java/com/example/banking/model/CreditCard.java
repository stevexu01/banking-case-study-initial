package com.example.banking.model;


import com.fasterxml.jackson.annotation.JsonInclude;

public class CreditCard {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String number;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String balance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String defaultMessage;


    public String getClientId() {
        return clientId;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getBalance() {
        return balance;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
