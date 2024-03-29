package com.kelvn.repository;

import com.kelvn.model.Account;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends BaseRepository<Account, UUID> {

  Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);
}
