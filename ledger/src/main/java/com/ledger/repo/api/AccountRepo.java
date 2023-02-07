package com.ledger.repo.api;

import com.ledger.repo.models.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends ReactiveCrudRepository<Account, Long> {
    Account findByCategory(String category);
}
