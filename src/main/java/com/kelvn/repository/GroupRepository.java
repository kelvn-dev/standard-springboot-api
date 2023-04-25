package com.kelvn.repository;

import com.kelvn.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID>, QuerydslPredicateExecutor<Group> {
}
