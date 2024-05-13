package com.example.socialmediaapp.controllers;

import com.example.socialmediaapp.services.DAOService;
import com.example.socialmediaapp.services.EmailServise;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TwoFAController {
    @Autowired
    EmailServise emailServise;
    @Autowired
    DAOService daoService;
    @RequestMapping(value = "users/{id}/emails/{emailId}/twofa", method = RequestMethod.PUT)
    public ResponseEntity<Object> send2faCodeEmail(@PathVariable("id") String id, @PathVariable("emailId") String emailId) throws MessagingException {
        String twoFaCode = String.valueOf(new Random().nextInt(9999) + 1000);
        emailServise.sendEmail(emailId, twoFaCode);
        daoService.update2FAProperties(id, twoFaCode);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
