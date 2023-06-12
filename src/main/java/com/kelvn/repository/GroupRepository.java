package com.kelvn.repository;

import com.kelvn.model.Group;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GroupRepository
		extends JpaRepository<Group, UUID>, QuerydslPredicateExecutor<Group> {}
