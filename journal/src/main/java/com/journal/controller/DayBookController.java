package com.journal.controller;

import com.journal.repo.models.DayBook;
import com.journal.service.DayBookService;
import com.journal.service.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity> ping() {
        return Mono.just(ResponseEntity.ok().body("pong"));
    }

    @PostMapping("/")
    public Mono<ResponseEntity> add(@RequestBody DayBook dayBook) {
        return dayBookService.add(dayBook)
                .map(daybook -> ResponseEntity.status(HttpStatus.CREATED).body(daybook))
                .cast(ResponseEntity.class)
                .onErrorResume((err) -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                ));
    }

    @GetMapping("/paginate")
    public Flux<ResponseEntity> paginate(
            @RequestParam(name = "type", required = false) Optional<TransactionType> transactionType,
            @RequestParam(name = "name", required = false) Optional<String> accountName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (transactionType.isEmpty()) {
            return dayBookService.paginate(page, size, Optional.empty())
                    .map(daybooks -> ResponseEntity.ok().body(daybooks))
                    .cast(ResponseEntity.class)
                    .onErrorResume((err) -> Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                    ));
        }
        return dayBookService.paginate(page, size, Optional.of(Pair.of(transactionType.get(), accountName.orElse(""))))
                .map(daybooks -> ResponseEntity.ok().body(daybooks))
                .cast(ResponseEntity.class)
                .onErrorResume((err) -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                ));
    }
}
