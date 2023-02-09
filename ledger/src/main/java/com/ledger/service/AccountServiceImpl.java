package com.ledger.service;

import com.ledger.repo.AccountCategoryRepo;
import com.ledger.repo.AccountRepo;
import com.ledger.repo.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountCategoryRepo accountCategoryRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public Mono<Account> insert(long accountCategoryId, Account account) {
        var result = accountCategoryRepo.findById(accountCategoryId).flatMap(ac -> {
                    account.setAccountCategory(ac);
                    return accountRepo.save(account);
                })
                .onErrorResume((err) -> Mono.error(
                        new RuntimeException("error inserting new account: ".concat(err.getMessage()))
                ));
        return result;
    }

    @Override
    public Mono<Account> findById(long id) {
        return accountRepo.findById(id).onErrorResume((err) -> Mono.error(
                new RuntimeException("error finding account by id: ".concat(err.getMessage()))
        ));
    }

    @Override
    public Mono<Account> findByName(String name) {
        return accountRepo.findByName(name).onErrorResume((err) -> Mono.error(
                new RuntimeException("error finding account by name: ".concat(err.getMessage()))
        ));
    }

    @Override
    public Flux<Account> findAll(int page, int size) {
        return accountRepo.findAll().onErrorResume((err) -> Mono.error(
                new RuntimeException("error finding all accounts: ".concat(err.getMessage()))
        )).take(size).skip(page);
    }
}
