package com.payment.pollen.services;

import com.payment.pollen.entities.User;
import com.payment.pollen.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServices
{
    @Autowired
    private IUserRepository userRepository;

    public void authenticateUser(User user) throws  Exception
    {
        // Assumption is that the UI send email and password
        // and the Backend store a new user if there is no such email in DB
        // or authenticate if the email is already stored
        System.out.println("BEfore ");

        Boolean exists = userRepository.existsById(user.getEmail());

        System.out.println("Exists : "+exists);
        //
        // Encrypt password first
        //
        String encryptedPassword = encryptPassword(user.getPassword());

        if(exists)
        {
            //
            // The user has already signed up
            //
            String savedPassword = userRepository.getPasswordFromEmail(user.getEmail());
            if(!savedPassword.equals(encryptedPassword))
            {
                throw new Exception("Password is invalid");
            }
        }
        else
        {
            //
            // Store new user with hashed password
            //
            System.out.println("Save user");
            user.setPassword(encryptedPassword);
            userRepository.save(user);
        }
    }


    private String encryptPassword(String rawPassword) throws Exception
    {
        String hex = null;
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));

            hex = DatatypeConverter.printHexBinary(hash);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new Exception("Error with password encryption");
        }

        return hex;
    }
}
