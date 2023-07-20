package com.kelvn.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.kelvn.model.Account;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository
    extends EntityGraphJpaRepository<Account, UUID>, EntityGraphQuerydslPredicateExecutor<Account> {

  Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);
}
