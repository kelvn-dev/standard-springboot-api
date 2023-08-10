package com.kelvn.repository;

import com.kelvn.model.GoogleAccount;
import java.util.Optional;
import java.util.UUID;

public interface GoogleAccountRepository extends BaseRepository<GoogleAccount, UUID> {
  Optional<GoogleAccount> findBySub(String sub);
}
