package com.ledger.repo.api;

import com.ledger.repo.models.AccountDetail;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepo extends ReactiveCrudRepository<AccountDetail, Long> {
    AccountDetail findByName(String name);
}
