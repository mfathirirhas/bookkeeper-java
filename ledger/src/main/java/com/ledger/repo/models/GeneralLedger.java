package com.ledger.repo.models;

import lombok.Builder;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Builder
@Table(name = "general_ledger")
public class GeneralLedger extends Base {

    @Column(name = "journal_id")
    @Valid
    @NotNull
    @NotEmpty
    private String journalId; // mongo id `_id` from journal service

    @Column(name = "timestamp")
    @Valid
    @NotNull
    @NotEmpty
    private Date timestamp;

    @Field(value = "debit")
    @Valid
    @NotNull(message = "debit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long debit;

    @Field(value = "debtor_account_id")
    @Valid
    @NotNull(message = "debtor_account_id cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long debtorAccountId;

    @Field(value = "credit")
    @Valid
    @NotNull(message = "credit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long credit;

    @Field(value = "creditor_account_id")
    @Valid
    @NotNull(message = "creditor_account_id cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long creditorAccountId;

    @Field(value = "desc")
    @Valid
    @NotNull(message = "desc cannot be null")
    @NotEmpty
    private String desc;
}
