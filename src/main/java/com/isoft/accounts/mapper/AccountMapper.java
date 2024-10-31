package com.isoft.accounts.mapper;

import com.isoft.accounts.dto.AccountDTO;
import com.isoft.accounts.entity.Account;

public class AccountMapper extends Mapper<Account, AccountDTO> {
    private static final AccountMapper accountMapper = new AccountMapper();

    private AccountMapper() {
    }

    public static AccountMapper get() {
        return accountMapper;
    }

    public AccountDTO entityToDTO(Account entity) {
        return (AccountDTO) super.entityToDTO(entity, AccountDTO.class);
    }

    public Account dTOToEntity(AccountDTO dto) {
        return (Account) super.dTOToEntity(dto, Account.class);
    }
}
