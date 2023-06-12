package com.kelvn.repository;

import com.kelvn.model.MetaAccount;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaAccountRepository extends JpaRepository<MetaAccount, UUID> {
	Optional<MetaAccount> findByMetaAccountId(String metaAccountId);
}
