// ---------------------------------------------------------------------------------------------------------------------
// Copyright (c) 2024 Piotr Marat
// ---------------------------------------------------------------------------------------------------------------------
package io.hands.on.ddd.account.infrastructure;

import static io.hands.on.ddd.account.infrastructure.InfrastructureConfiguration.BeanNames.ACCOUNT_JDBC_REPOSITORY;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// -------------------------------------------------------------------------------------------------------------------------------------------------------------
// Implementation
// -------------------------------------------------------------------------------------------------------------------------------------------------------------

/** Spring Data JDBC repository for persistence on Account aggregate. */
@Repository(ACCOUNT_JDBC_REPOSITORY)
public interface AccountJdbcRepository extends CrudRepository<AccountJdbcEntity, UUID> {
  Optional<AccountJdbcEntity> findByEmail(String email);
}
