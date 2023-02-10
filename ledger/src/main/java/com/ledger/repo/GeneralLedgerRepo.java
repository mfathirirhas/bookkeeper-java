package com.ledger.repo;

import com.ledger.repo.models.GeneralLedger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface GeneralLedgerRepo extends ReactiveCrudRepository<GeneralLedger, Long> {
    Flux<GeneralLedger> findByDebtorAccountId(long debtorAccountId, Pageable pageable);

    Flux<GeneralLedger> findByCreditorAccountId(long creditorAccountId, Pageable pageable);
}
