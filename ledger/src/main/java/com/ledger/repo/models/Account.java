package com.ledger.repo.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;

@Entity
@Builder
@Table(name = "accounts")
public class Account extends Base {

    @Column(name = "name", unique = true)
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "desc")
    @NotNull
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_category_id", nullable = false)
    private AccountCategory accountCategory;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GeneralLedger> generalLedgers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public AccountCategory getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(AccountCategory accountCategory) {
        this.accountCategory = accountCategory;
    }

    public List<GeneralLedger> getGeneralLedgers() {
        return generalLedgers;
    }

    public void setGeneralLedgers(List<GeneralLedger> generalLedgers) {
        this.generalLedgers = generalLedgers;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", accountCategory=" + accountCategory +
                ", generalLedgers=" + generalLedgers +
                '}';
    }
}
