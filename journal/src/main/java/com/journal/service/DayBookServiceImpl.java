package com.journal.service;

import com.journal.repo.DayBookRepository;
import com.journal.repo.models.DayBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class DayBookServiceImpl implements DayBookService {

    @Autowired
    private DayBookRepository dayBookRepository;

    @Override
    public Mono<DayBook> add(DayBook dayBook) {
        if (dayBook.getTimestamp() == null) {
            dayBook.setTimestamp(Date.from(Instant.now()));
        }
        return dayBookRepository.insert(dayBook)
                .onErrorResume((err) -> Mono.error(new RuntimeException("Error inserting new journal entry: ".concat(err.getMessage()))));
    }

    @Override
    public Flux<DayBook> paginate(int page, int size, Optional<Pair<TransactionType, Long>> trx) {
        page = page < 1? 0 : page;
        size = size < 1? 10 : size;
        Pageable paging = PageRequest.of(page,size);
        if (trx.isPresent()) {
            var trxObj = trx.get();
            if (trxObj.getFirst().equals(TransactionType.DEBIT)) {
                return dayBookRepository.findByDebtorAccountId(trxObj.getSecond(), paging)
                        .onErrorResume((err) -> Flux.error(new RuntimeException("Error getting paginated journal: ".concat(err.getMessage()))));
            }
            return dayBookRepository.findByCreditorAccountId(trxObj.getSecond(), paging)
                    .onErrorResume((err) -> Flux.error(new RuntimeException("Error getting paginated journal: ".concat(err.getMessage()))));
        }
        return dayBookRepository.findAll().take(size).skip(page)
                .onErrorResume((err) -> Flux.error(new RuntimeException("Error getting paginated journal: ".concat(err.getMessage()))));
    }
}
