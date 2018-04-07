package com.payment.pollen.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payment.pollen.entities.BankAccount;
import com.payment.pollen.entities.Iban;
import com.payment.pollen.entities.User;
import com.payment.pollen.repositories.IBankRepository;
import com.payment.pollen.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class BankServices
{
    String  s = "https://openiban.com/validate/GB05LOYD30942130275860?getBIC=true&validateBankCode=true";
    private static final String OPEN_IBAN_URI = "https://openiban.com/validate/IBAN?&validateBankCode=true";

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBankRepository bankRepository;

    public void addBankDetails(BankAccount bankAccount) throws Exception
    {
        //
        // Second part of validation
        // Verify that the user exists
        // and that the Iban is valid
        //
        verifyBankDetails(bankAccount);
        bankRepository.save(bankAccount);

    }

    private void verifyBankDetails(BankAccount bankAccount) throws Exception
    {
        verifyUser(bankAccount.getCustomerId());
        verifyIban(bankAccount.getIban());
    }


    private void verifyUser(long userId) throws Exception
    {
        boolean verified = userRepository.checkIfUserExistsFromId(userId);
        if(!verified)
        {
            throw new Exception("The user does not exists");
        }
    }

    private void verifyIban(String ibanNumber) throws Exception
    {
        String url = OPEN_IBAN_URI.replace("IBAN", ibanNumber);

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String responseBody = response.getBody();

     //   try
   //     {
            Iban iban = mapper.readValue(responseBody, Iban.class);
            if(!iban.isValid())
            {
                throw new Exception("The IBAN provided is not valid");
            }
       // }
//        catch (IOException exception)
//        {
//
//        }

    }

}
