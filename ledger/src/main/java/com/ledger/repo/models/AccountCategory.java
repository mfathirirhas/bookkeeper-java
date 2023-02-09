package com.ledger.repo.models;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;

@Entity
@Builder
@Table(name = "accounts")
public class AccountCategory extends Base {

    @Column(name = "category")
    private String category;

    @Column(name = "desc")
    private String desc;

    @OneToMany(mappedBy = "bk/accountCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Account> accountList;
}
