package com.example.banking.repository;

import com.example.banking.model.AutoLoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface AutoLoanRepository extends CrudRepository<AutoLoan, Long> {

    public List<AutoLoan> findByClientId(String clientId);
}
