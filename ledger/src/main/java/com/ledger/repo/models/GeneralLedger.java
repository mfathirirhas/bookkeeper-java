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
    @NotNull
    @NotEmpty
    private String journalId; // mongo id `_id` from journal service

    @Column(name = "timestamp")
    @NotNull
    @NotEmpty
    private Date timestamp;

    @Field(value = "debit")
    @Valid
    @NotNull(message = "debit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long debit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_account_id", nullable = false)
    private Account account;

    @Field(value = "credit")
    @Valid
    @NotNull(message = "debit cannot be null")
    @Range(min = 1, message = "cannot be 0")
    private long credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_account_id", nullable = false)
    private long creditorAccountId; // account id of creditor
}
