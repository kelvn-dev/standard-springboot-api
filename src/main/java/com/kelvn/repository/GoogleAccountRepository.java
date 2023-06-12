package com.kelvn.repository;

import com.kelvn.model.GoogleAccount;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, UUID> {
	Optional<GoogleAccount> findBySub(String sub);
}
