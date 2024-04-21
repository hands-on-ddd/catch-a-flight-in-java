// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

package io.hands.on.ddd.bestdeal.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static io.hands.on.ddd.bestdeal.infrastructure.InfrastructureConfiguration.BeanNames.ACCOUNT_JDBC_REPOSITORY;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * Spring Data JDBC repository for persistence on Account aggregate.
 */
@Repository(ACCOUNT_JDBC_REPOSITORY)
public interface AccountJdbcRepository extends CrudRepository<AccountJdbcEntity, UUID> {
    Optional<AccountJdbcEntity> findByEmail(String email);
}
