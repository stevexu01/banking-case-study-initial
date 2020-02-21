package com.example.banking.repository;

import com.example.banking.model.CreditCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {
    public List<CreditCard> findByClientId(String clientId);

}
