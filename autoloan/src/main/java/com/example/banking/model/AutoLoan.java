package com.example.banking.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AutoLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double balance;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String defaultMessage;

    public Long getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
        return "AutoLoan{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
