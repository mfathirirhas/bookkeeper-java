package com.ledger.repo;

import com.ledger.repo.models.AccountCategory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AccountCategoryRepo extends ReactiveCrudRepository<AccountCategory, Long> {
    Mono<AccountCategory> findByCategory(String category);
}
