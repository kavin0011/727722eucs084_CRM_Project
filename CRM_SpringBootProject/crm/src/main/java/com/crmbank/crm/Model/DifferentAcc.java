package com.crmbank.crm.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DifferentAcc {
    @Id
    int id;
    String email;
    int accountnumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    Bank bank;
}
