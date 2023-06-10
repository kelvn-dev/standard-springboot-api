package com.kelvn.repository;

import com.kelvn.model.GoogleAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, UUID> {
  Optional<GoogleAccount> findBySub(String sub);
}
