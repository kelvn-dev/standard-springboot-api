package com.kelvn.repository;

import com.kelvn.model.MetaAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MetaAccountRepository extends JpaRepository<MetaAccount, UUID> {
  Optional<MetaAccount> findByMetaAccountId(String metaAccountId);
}
