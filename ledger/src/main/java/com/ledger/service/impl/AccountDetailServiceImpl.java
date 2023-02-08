package com.ledger.service.impl;

import com.ledger.repo.AccountDetailRepo;
import com.ledger.repo.models.AccountDetail;
import com.ledger.service.api.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Transactional
public class AccountDetailServiceImpl implements AccountDetailService {

    @Autowired
    private AccountDetailRepo accountDetailRepo;

    @Override
    public Flux<AccountDetail> findAllAccountDetails() {
        return accountDetailRepo.findAll();
    }

    @Override
    public Mono<AccountDetail> findById(long id) {
        return accountDetailRepo.findById(id);
    }

    @Override
    public Mono<AccountDetail> insert(long accountId, AccountDetail accountDetail) {
        return accountDetailRepo.save(accountDetail);
    }
}
