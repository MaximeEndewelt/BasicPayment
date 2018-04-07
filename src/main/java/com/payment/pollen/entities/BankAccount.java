package com.payment.pollen.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount
{
    @Id
    private String name;

    private String iban;

    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
