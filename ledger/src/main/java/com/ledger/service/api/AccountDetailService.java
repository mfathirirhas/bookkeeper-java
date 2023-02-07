package com.ledger.service.api;

import com.ledger.repo.models.AccountDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountDetailService {

    Flux<AccountDetail> findAllAccountDetails();

    Mono<AccountDetail> findById(long id);

    Mono<AccountDetail> insert(long accountId, AccountDetail accountDetail);

}
