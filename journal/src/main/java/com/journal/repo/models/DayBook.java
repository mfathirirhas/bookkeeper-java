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
    private long debit;

    @Field(value = "debtor_account_id")
    private String debtorAccountId; // sub account id of debtor

    @Field(value = "credit")
    private long credit;

    @Field(value = "creditor_account_id")
    private String creditorAccountId; // sub account id of creditor

    @Field(value = "desc")
    private String desc;
}
