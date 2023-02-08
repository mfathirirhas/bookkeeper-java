package com.journal.service;

import com.journal.repo.models.DayBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    @Autowired
    private KafkaTemplate<String, DayBook> kafkaTemplate;

    @Override
    public Completable publishDaybook(String topic, DayBook dayBook) {
        return Completable.create(completableEmitter -> {
                    kafkaTemplate.send(topic,dayBook);
                    completableEmitter.onComplete();
                }).subscribeOn(scheduler);
    }
}