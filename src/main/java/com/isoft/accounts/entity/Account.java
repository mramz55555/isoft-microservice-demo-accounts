package com.isoft.accounts.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Account extends BaseEntity {
    private Long customerId;
    private String accountType;
    private String branchAddress;
}
