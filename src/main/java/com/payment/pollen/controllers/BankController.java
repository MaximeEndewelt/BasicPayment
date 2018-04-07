package com.payment.pollen.controllers;

import com.payment.pollen.entities.BankAccount;
import com.payment.pollen.entities.Code;
import com.payment.pollen.services.BankServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@RestController
public class BankController
{
    @Autowired
    BankServices bankServices;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addBank(@RequestBody BankAccount bankAccount)
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

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public ResponseEntity<?> shareCode(@RequestBody Code code)
    {
        try
        {
            validateSharedCode(code);
            bankServices.shareDetails(code);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ExceptionConverter.convertException(e);
        }
    }

    @RequestMapping(value = "/getSharedCodes/{email}", method = RequestMethod.GET)
    public ResponseEntity<?> getSharedCodes(@PathVariable("email") String email)
    {
        try
        {
            List<Code> codes = bankServices.getSharedDetails(email);
            return ResponseEntity.status(HttpStatus.OK).body(codes);
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
        String userEmail = bankAccount.getUserEmail();

        if(name == null || name.equals(""))
        {
            throw new IllegalArgumentException("The name of the bank must be provided");
        }

        if(iban == null || iban.equals(""))
        {
            throw new IllegalArgumentException("The iban must be provided");
        }

        if(userEmail == null || userEmail.equals(""))
        {
            throw new IllegalArgumentException("The user email must be provided");
        }
    }

    private void validateSharedCode(Code sharedCode)
    {
        String userEmail = sharedCode.getUserEmail();
        String recipientEmail = sharedCode.getRecipientEmail();
        String code = sharedCode.getCode();

        if(userEmail == null || userEmail.equals(""))
        {
            throw new IllegalArgumentException("The user email must be provided");
        }

        if(recipientEmail == null || recipientEmail.equals(""))
        {
            throw new IllegalArgumentException("The recipient email must be provided");
        }

        if(code == null || code.equals(""))
        {
            throw new IllegalArgumentException("The code must be provided");
        }
    }
}
