package com.journal.service;

import com.journal.repo.models.DayBook;
import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface DayBookService {
    Mono<DayBook> add(DayBook dayBook);

    Flux<DayBook> paginate(int page, int size, Optional<Pair<TransactionType, Long>> trx);
}
