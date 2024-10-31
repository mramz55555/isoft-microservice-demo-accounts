package com.isoft.accounts.repository;

import com.isoft.accounts.entity.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends BaseRepository<Account> {
    Optional<Account> findByCustomerId(Long customerId);

    @Modifying
    void deleteByCustomerId(Long customerId);
}
