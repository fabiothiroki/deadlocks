package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transfer(Long sourceAccountId, Long destinationAccountId, int amount) {
        Optional<AccountModel> sourceAccountOptional = accountRepository.findById(sourceAccountId);
        if (sourceAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Source account not found");
        }

        Optional<AccountModel> destinationAccountOptional = accountRepository.findById(destinationAccountId);
        if (destinationAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Destination account not found");
        }

        accountRepository.updateBalance(sourceAccountId, -amount);
        accountRepository.updateBalance(destinationAccountId, amount);
    }
}
