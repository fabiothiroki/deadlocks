package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String index() {
        return "Hello, World2!";
    }

    @PostMapping("/accounts")
    public AccountModel createAccount(@RequestParam String name, @RequestParam int balance) {
        AccountModel newAccount = new AccountModel(name, balance);
        return accountRepository.save(newAccount);
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transfer(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam int amount) {
        try {
            accountService.transfer(sourceAccountId, destinationAccountId, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
