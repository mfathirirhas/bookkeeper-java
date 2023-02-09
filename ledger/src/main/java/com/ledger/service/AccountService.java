package com.ledger.service;

import com.ledger.repo.models.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {
    Mono<Account> insert(long accountCategoryId, Account account);

    Mono<Account> findById(long id);

    Mono<Account> findByName(String name);

    Flux<Account> findAll(int page, int size);
}
