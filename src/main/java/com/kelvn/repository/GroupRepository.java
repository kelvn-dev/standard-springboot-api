package com.kelvn.repository;

import com.kelvn.model.AppGroup;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupRepository
    extends JpaRepository<AppGroup, UUID>, QuerydslPredicateExecutor<AppGroup> {

  //  @Override
  //  @EntityGraph(attributePaths = "accounts")
  //  Optional<AppGroup> findById(UUID id);
}
