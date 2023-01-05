package com.example.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account a WHERE a.account_no = :accountNo and a.status = 'OPEN'", nativeQuery = true)
    Optional<Account> findByAccountNoAndStatusEqualsOpen(@Param("accountNo")Long accountNo);
}
