package com.ledger.controller;

import com.ledger.repo.models.Account;
import com.ledger.repo.models.AccountDetail;
import com.ledger.service.api.AccountDetailService;
import com.ledger.service.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/ledger")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDetailService accountDetailService;

    @GetMapping("/ping")
    public Mono<String> ping() {
        return Mono.just("pong");
    }

    @GetMapping("/{id}")
    public Flux<Account> getAccount(@PathVariable Optional<Long> accountId) {
        if (accountId.isPresent()) {
            return Flux.from(accountService.findById(accountId.get()).map(ac -> ac).or(Mono.empty()));
        }
        return accountService.findAllAccounts();
    }

    @GetMapping("/account_detail/{id}")
    public Flux<AccountDetail> getAccountDetail(@PathVariable Optional<Long> accountDetailId) {
        if (accountDetailId.isPresent()) {
            return Flux.from(accountDetailService.findById(accountDetailId.get()));
        }
        return accountDetailService.findAllAccountDetails();
    }

    @PostMapping("")
    public Mono<Account> addAccount(@RequestBody Account account) {
        return accountService.insert(account);
    }

    @PostMapping("/{id}/detail")
    public Mono<AccountDetail> addAccountDetail(
            @PathVariable long accountId,
            @RequestBody AccountDetail accountDetail) {
        return accountDetailService.insert(accountId, accountDetail);
    }
}
