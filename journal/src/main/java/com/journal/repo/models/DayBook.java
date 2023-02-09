package com.journal.repo.models;

import lombok.Data;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
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
    @Valid
    @NotNull(message = "debit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long debit;

    @Field(value = "debtor_account_id")
    @Valid
    @NotNull(message = "debtorAccountId cannot be null")
    @Range(min = 1, message = "debtorAccountId cannot be 0")
    private long debtorAccountId; // account id of debtor

    @Field(value = "credit")
    @Valid
    @NotNull(message = "credit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long credit;

    @Field(value = "creditor_account_id")
    @Valid
    @NotNull(message = "creditorAccountId cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long creditorAccountId; // account id of creditor

    @Field(value = "desc")
    @NotNull(message = "debit cannot be null")
    private String desc;
}
