package com.ledger.repo.models;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import org.springframework.data.relational.core.mapping.Table;
import java.util.List;

@Entity
@Builder
@Table(name = "account_categories")
public class AccountCategory extends Base {

    @Column(name = "category", unique = true)
    @NotNull
    @NotEmpty
    private String category;

    @Column(name = "desc")
    @NotNull
    private String desc;

    @OneToMany(mappedBy = "account_category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "AccountCategory{" +
                "category='" + category + '\'' +
                ", desc='" + desc + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
