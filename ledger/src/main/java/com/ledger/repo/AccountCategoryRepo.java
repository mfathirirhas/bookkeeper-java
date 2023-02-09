package com.ledger.repo;

import com.ledger.repo.models.AccountCategory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCategoryRepo extends ReactiveCrudRepository<AccountCategory, Long> {

}
