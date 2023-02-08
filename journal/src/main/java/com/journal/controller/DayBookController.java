package com.journal.controller;

import com.journal.repo.models.DayBook;
import com.journal.service.DayBookService;
import com.journal.service.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class DayBookController {

    @Autowired
    private DayBookService dayBookService;

    @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity> ping() {
        return Mono.just(ResponseEntity.ok().body("pong"));
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity> add(@RequestBody @Valid DayBook dayBook) {
        return dayBookService.add(dayBook)
                .map(daybook -> ResponseEntity.status(HttpStatus.CREATED).body(daybook))
                .cast(ResponseEntity.class)
                .onErrorResume((err) -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                ));
    }

    @GetMapping("/paginate")
    public Mono<ResponseEntity> paginate(
            @RequestParam(name = "type", required = false) Optional<TransactionType> transactionType,
            @RequestParam(name = "name", required = false) Optional<Long> accountName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (transactionType.isEmpty()) {
            return dayBookService.paginate(page, size, Optional.empty()).collectList()
                    .map(daybooks -> ResponseEntity.ok().body(daybooks))
                    .cast(ResponseEntity.class)
                    .onErrorResume((err) -> Mono.just(
                            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                    ));
        }
        return dayBookService.paginate(page, size, Optional.of(Pair.of(transactionType.get(), accountName.orElse(Long.valueOf(0)))))
                .map(daybooks -> ResponseEntity.ok().body(daybooks)).collectList()
                .cast(ResponseEntity.class)
                .onErrorResume((err) -> Mono.just(
                        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error".concat(err.getMessage()))
                ));
    }
}
