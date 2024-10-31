package com.isoft.accounts.validator;

import com.isoft.accounts.dto.AccountDTO;
import com.isoft.accounts.entity.Account;

public class AccountValidator extends Validator<Account, AccountDTO> {
    public AccountValidator() {
    }

    public void validate(AccountDTO dto) {
        super.validate(dto);
    }
}
