package com.ledger.repo;

import com.ledger.repo.models.Account;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountRepo extends ReactiveCrudRepository<Account, Long> {
    Mono<Account> findByName(String name);
}
