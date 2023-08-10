package com.kelvn.repository;

import com.kelvn.model.MetaAccount;
import java.util.Optional;
import java.util.UUID;

public interface MetaAccountRepository extends BaseRepository<MetaAccount, UUID> {
  Optional<MetaAccount> findByMetaAccountId(String metaAccountId);
}
