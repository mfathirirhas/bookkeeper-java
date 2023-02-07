package com.ledger.service.impl;

import com.ledger.repo.api.AccountRepo;
import com.ledger.repo.models.Account;
import com.ledger.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Flux<Account> findAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public Mono<Account> findById(long id) {
        return accountRepo.findById(id);
    }

    @Override
    public Mono<Account> insert(Account account) {
        return accountRepo.save(account);
    }
}
