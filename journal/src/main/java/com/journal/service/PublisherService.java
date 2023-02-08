package com.journal.service;

import com.journal.repo.models.DayBook;
import io.reactivex.Completable;

public interface PublisherService {
    Completable publishDaybook(String topic, DayBook dayBook);
}
