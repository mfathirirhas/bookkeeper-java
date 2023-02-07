package com.journal.controller;

import com.journal.repo.models.DayBook;
import com.journal.service.DayBookService;
import com.journal.service.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class DayBookController {

    @Autowired
    private DayBookService dayBookService;

    @GetMapping("/ping")
    public Mono<String> ping() {
        return Mono.just("pong");
    }

    @PostMapping("/")
    public Mono<DayBook> add(@RequestBody DayBook dayBook) {
        return dayBookService.add(dayBook);
    }

    @GetMapping("/paginate")
    public Flux<DayBook> paginate(
            @RequestParam(name = "type", required = false) Optional<TransactionType> transactionType,
            @RequestParam(name = "name", required = false) Optional<String> accountName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (transactionType.isEmpty()) {
            return dayBookService.paginate(page, size, Optional.empty());
        }
        return dayBookService.paginate(page, size, Optional.of(Pair.of(transactionType.get(), accountName.orElse(""))));
    }
}
