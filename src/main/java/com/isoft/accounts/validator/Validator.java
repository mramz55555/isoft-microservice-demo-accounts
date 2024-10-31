package com.isoft.accounts.validator;

import com.isoft.accounts.dto.BaseDTO;
import com.isoft.accounts.entity.BaseEntity;

import java.util.Objects;

public abstract class Validator<T extends BaseEntity, S extends BaseDTO> {
    public Validator() {
    }

    public void validate(S dto) {
        Objects.requireNonNull(dto);
    }
}
