package com.example.banking.service;

import com.example.banking.model.AutoLoan;
import com.example.banking.repository.AutoLoanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<AutoLoan> getAllLoans() {
        List<AutoLoan> allLoans = new ArrayList<AutoLoan>();

        Iterable iterable = this.repository.findAll();

        iterable.forEach((loan)->allLoans.add((AutoLoan) loan));

        return allLoans;
    }

    public AutoLoan updateLoan(Long id, AutoLoan autoLoan) {
        AutoLoan updatedLoan = null;

        Optional<AutoLoan> loanOptional = this.repository.findById(id);
        if(loanOptional.isPresent()){
            updatedLoan = this.repository.save(loanOptional.get());
        } else {
            //TODO: fix not
        }

        updatedLoan.setClientId(autoLoan.getClientId());
        updatedLoan.setName(autoLoan.getName());

        //TODO: fix - update not persisted
        return updatedLoan;
    }


    public void deleteLoan(Long id) {
         this.repository.deleteById(id);
    }
}
