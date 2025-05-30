package com.eazybytes.springsection1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class Accounts {
    @Id
    @Column(name="account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private Date createDt;
}
