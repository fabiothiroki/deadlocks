package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String index() {
        return "Hello, World!";
    }

    @PostMapping("/accounts")
    public AccountModel createAccount(@RequestParam String name, @RequestParam int balance) {
        AccountModel newAccount = new AccountModel(name, balance);
        return accountRepository.save(newAccount);
    }

    @PutMapping("/accounts/{id}/deposit")
    public void deposit(@PathVariable Long id, @RequestParam int amount) {
        accountRepository.updateBalance(id, amount);
    }
}
