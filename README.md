# Deadlock application

A demo application to show how deadlocks can occur in a multi-threaded environment.

# How to run
```bash
./mvnw spring-boot:run
```

# Testing a simple transfer
## Create source and destinations accounts

```bash
curl -X POST -F "name=source" -F "balance=100" http://localhost:8089/accounts
curl -X POST -F "name=destination" -F "balance=0" http://localhost:8089/accounts
curl -X POST "http://localhost:8089/accounts/transfer" -d "sourceAccountId=1" -d "destinationAccountId=2" -d "amount=100"
```