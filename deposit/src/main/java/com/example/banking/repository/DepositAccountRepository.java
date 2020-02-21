package com.example.banking.repository;

import com.example.banking.model.DepositAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositAccountRepository extends CrudRepository<DepositAccount, Long> {
    public List<DepositAccount> findByClientId(String clientId);
}
