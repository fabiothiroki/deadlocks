package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE AccountModel a SET a.balance = a.balance + :amount WHERE a.id = :id")
    void updateBalance(@Param("id") Long id, @Param("amount") int amount);
}
