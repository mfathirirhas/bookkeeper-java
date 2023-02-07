package com.journal.repo.models;

import lombok.Data;
import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@Document(collection = "daybooks")
public class DayBook extends Base {

    @Field(value = "timestamp")
    private Date timestamp;

    @Field(value = "debit")
    private long debit; // expenses

    @Field(value = "debtor")
    private String debtor; // account name of debtor, case insensitive

    @Field(value = "credit")
    private long credit; // gains

    @Field(value = "creditor")
    private String creditor; // account name of creditor, case insensitive

    @Field(value = "desc")
    private String desc;
}
