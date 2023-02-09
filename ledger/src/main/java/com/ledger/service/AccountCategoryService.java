package com.ledger.service;

import com.ledger.repo.AccountCategoryRepoImpl;
import com.ledger.repo.models.AccountCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountCategoryService {
    Mono<AccountCategory> insert(AccountCategory accountCategory);

    Flux<AccountCategory> findAll(int page, int size);

    Mono<AccountCategory> findById(long id);

    Mono<AccountCategory> findByCategory(String category);
}
