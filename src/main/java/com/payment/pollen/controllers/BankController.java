package com.payment.pollen.controllers;

import com.payment.pollen.entities.BankAccount;
import com.payment.pollen.services.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bank")
@RestController
public class BankController
{
    @Autowired
    BankServices bankServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addBank(BankAccount bankAccount)
    {
        try
        {
            validateBankDetails(bankAccount);
            bankServices.addBankDetails(bankAccount);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ExceptionConverter.convertException(e);
        }
    }

    private void validateBankDetails(BankAccount bankAccount) throws Exception
    {
        String name = bankAccount.getName();
        String iban = bankAccount.getIban();
        long customerId = bankAccount.getCustomerId();

        if(name == null || name.equals(""))
        {
            throw new IllegalArgumentException("The name of the bank must be provided");
        }

        if(iban == null || iban.equals(""))
        {
            throw new IllegalArgumentException("The iban must be provided");
        }

        if(customerId < 0)
        {
            throw new IllegalArgumentException("The customer Id must be provided");
        }
    }
}
