package com.ledger.repo.models;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Builder
@Table(name = "account_details")
public class AccountDetail extends Base {

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
