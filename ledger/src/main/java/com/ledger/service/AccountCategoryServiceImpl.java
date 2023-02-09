package com.ledger.service;

import com.ledger.repo.AccountCategoryRepo;
import com.ledger.repo.models.AccountCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountCategoryServiceImpl implements AccountCategoryService {

    @Autowired
    private AccountCategoryRepo accountCategoryRepo;

    @Override
    public Mono<AccountCategory> insert(AccountCategory accountCategory) {
        return accountCategoryRepo.save(accountCategory)
                .onErrorResume((err) -> Mono.error(
                        new RuntimeException("error inserting new account category: ".concat(err.getMessage()))
                ));
    }

    @Override
    public Flux<AccountCategory> findAll(int page, int size) {
        return accountCategoryRepo.findAll()
                .onErrorResume((err) -> Flux.error(
                        new RuntimeException("error getting all account categories: ".concat(err.getMessage()))
                )).take(size).skip(page);
    }

    @Override
    public Mono<AccountCategory> findById(long id) {
        return accountCategoryRepo.findById(id)
                .onErrorResume((err) -> Mono.error(
                        new RuntimeException("error getting account category by id: ".concat(err.getMessage()))
                ));
    }

    @Override
    public Mono<AccountCategory> findByCategory(String category) {
        return accountCategoryRepo.findByCategory(category)
                .onErrorResume((err) -> Mono.error(
                        new RuntimeException("error getting account category by category: ".concat(err.getMessage()))
                ));
    }
}
