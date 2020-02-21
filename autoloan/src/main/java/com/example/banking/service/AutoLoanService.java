package com.example.banking.service;

import com.example.banking.model.AutoLoan;
import com.example.banking.repository.AutoLoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoLoanService {
    private AutoLoanRepository repository;

    public AutoLoanService(AutoLoanRepository repository) {
        this.repository = repository;
    }

    public AutoLoan createAutoLoan(AutoLoan autoLoan) {
        //TODO: fix
        return this.repository.save(autoLoan);
    }

    public List<AutoLoan> getLoansByClientId(String clientId) {

        //TODO: fix
        return this.repository.findByClientId(clientId);
    }
}
