package com.example.banking.service;

import com.example.banking.model.DepositAccount;
import com.example.banking.repository.DepositAccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositAccountService {
    private DepositAccountRepository repository;

    public DepositAccountService(DepositAccountRepository repository) {
        this.repository = repository;
    }


    public DepositAccount createDepositAccount(DepositAccount depositAccount) {
        return this.repository.save(depositAccount);
    }

    public List<DepositAccount> getDepositAccountsByClientId(String clientId) {
        return this.repository.findByClientId(clientId);
    }

    public List<DepositAccount> getAllDepositAccounts() {
        final List<DepositAccount> depositAccounts = new ArrayList<DepositAccount>();

        Iterable iterable = this.repository.findAll();

        iterable.forEach((dep)->depositAccounts.add((DepositAccount) dep));

        return depositAccounts;
    }

    public DepositAccount updateDepositAccount(Long id, DepositAccount depositAccount) {
        DepositAccount depositAccountFound = null;

        Optional<DepositAccount> creditCardOption = this.repository.findById(id);
        if(creditCardOption.isPresent()){
            depositAccountFound = this.repository.save(creditCardOption.get());
        } else {
            //TODO: fix not
        }

        depositAccountFound.setAccountNumber(depositAccount.getAccountNumber());
        depositAccountFound.setClientId(depositAccount.getClientId());
        depositAccountFound.setName(depositAccount.getName());

        this.repository.save(depositAccountFound);


        return depositAccountFound;
    }

    public void deleteDepositAccount(Long id) {
        this.repository.deleteById(id);
    }
}
