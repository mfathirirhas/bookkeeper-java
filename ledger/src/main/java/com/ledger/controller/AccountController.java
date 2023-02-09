package com.ledger.controller;

import com.ledger.repo.models.AccountCategory;
import com.ledger.repo.models.Account;
import com.ledger.service.AccountCategoryService;
import com.ledger.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/ledger")
public class AccountController {

    @Autowired
    private AccountCategoryService accountCategoryService;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BaseResponse> ping() {
        return Mono.just(BaseResponse.Ok("pong"));
    }


    @PostMapping(value = "/account_category", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BaseResponse> addAccountCategory(@RequestBody @Valid AccountCategory accountCategory) {
        return accountCategoryService.insert(accountCategory)
                .cast(BaseResponse.class)
                .onErrorResume((err) -> Mono.just(
                        BaseResponse.InternalError("Error ", err.getMessage())
                ));
    }

    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BaseResponse> addAccount(
            @RequestParam(name = "category_id") long accountCategoryId,
            @RequestBody @Valid Account account) {
        return accountService.insert(accountCategoryId, account)
                .cast(BaseResponse.class)
                .onErrorResume((err) -> Mono.just(
                        BaseResponse.InternalError("Error ", err.getMessage())
                ));
    }

    @GetMapping(value = "/account_category", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BaseResponse> findAccountCategory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accountCategoryService.findAll(page, size).collectList()
                .map(accountCategories -> BaseResponse.Ok(accountCategories))
                .cast(BaseResponse.class)
                .onErrorResume((err) -> Mono.just(
                        BaseResponse.InternalError("Error ", err.getMessage())
                ));
    }

    @GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<BaseResponse> findAccount(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return accountService.findAll(page, size).collectList()
                .map(accounts -> BaseResponse.Ok(accounts))
                .cast(BaseResponse.class)
                .onErrorResume((err) -> Mono.just(
                        BaseResponse.InternalError("Error ", err.getMessage())
                ));
    }

}
