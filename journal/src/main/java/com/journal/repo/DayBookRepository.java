package com.journal.repo;

import com.journal.repo.models.DayBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DayBookRepository extends ReactiveMongoRepository<DayBook, Long> {

    Flux<DayBook> findByDebtorAccountId(long debtorAccountId, Pageable pageable);

    Flux<DayBook> findByCreditorAccountId(long creditorAccountId, Pageable pageable);
}
