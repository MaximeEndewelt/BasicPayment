package com.payment.pollen.controllers;

import com.payment.pollen.entities.User;
import com.payment.pollen.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class LoginController
{
    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<?> signUp(@RequestBody  User user)
    {
        try
        {
            validateUserInput(user);
            userServices.authenticateUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ExceptionConverter.convertException(e);
        }
    }

    private void validateUserInput(User user)
    {
        System.out.println("USer email : "+user.getEmail()+ " - password : "+user.getPassword());
        String email = user.getEmail();
        String password = user.getPassword();

        if(email == null || email.isEmpty())
        {
            throw new IllegalArgumentException(ExceptionConverter.NO_EMAIL);
        }

        if(password == null || password.isEmpty())
        {
            throw new IllegalArgumentException(ExceptionConverter.NO_PASSWORD);
        }
    }
}
