package com.kelvn.repository;

import com.kelvn.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID>, QuerydslPredicateExecutor<Account> {

  Optional<Account> findByUsername(String username);
  Optional<Account> findByEmail(String email);

}
