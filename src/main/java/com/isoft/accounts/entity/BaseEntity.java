package com.isoft.accounts.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @CreatedBy
    @Column(
            updatable = false
    )
    private String createdUser;
    @CreatedDate
    @Column(
            updatable = false
    )
    private LocalDate createdDate;
    @LastModifiedBy
    @Column(
            insertable = false
    )
    private String updatedUser;
    @LastModifiedDate
    @Column(
            insertable = false
    )
    private LocalDate updatedDate;
}
