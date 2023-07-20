package com.kelvn.repository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphJpaRepository;
import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQuerydslPredicateExecutor;
import com.kelvn.model.AppGroup;
import java.util.UUID;

public interface GroupRepository
    extends EntityGraphJpaRepository<AppGroup, UUID>,
        EntityGraphQuerydslPredicateExecutor<AppGroup> {}
