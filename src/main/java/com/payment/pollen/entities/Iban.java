package com.payment.pollen.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Iban
{
    private boolean valid;

    @JsonIgnore
    private String[] messages;
    private String iban;
    private Object bankData;
    @JsonIgnore
    private String checkResults;

    public boolean isValid() {
        return valid;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Object getBankData() {
        return bankData;
    }

    public void setBankData(Object bankData) {
        this.bankData = bankData;
    }

    public String getCheckResults() {
        return checkResults;
    }

    public void setCheckResults(String checkResults) {
        this.checkResults = checkResults;
    }
}
