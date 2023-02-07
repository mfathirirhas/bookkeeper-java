package com.ledger.service.api;

import com.ledger.repo.models.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

    Flux<Account> findAllAccounts();

    Mono<Account> findById(long id);

    Mono<Account> insert(Account account);

}
