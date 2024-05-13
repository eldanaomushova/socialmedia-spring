//package com.example.socialmediaapp.services;
//
//import com.twilio.Twilio;
//import com.twilio.type.PhoneNumber;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.stereotype.Service;
//import jakarta.mail.*;
//
//@Service
//public class SMSService {
//
//    private final static String ACCOUNT_SID = "<ACCOUNT_SID>";
//    private final static String AUTH_ID = "**********************";
//
//    static {
//        Twilio.init(ACCOUNT_SID, AUTH_ID);
//    }
//
//    public boolean send2FaCode(String mobilenumber, String twoFaCode) {
//
//        Message message = new MimeMessage(session);
//
//        Message.creator(new PhoneNumber(mobilenumber), new PhoneNumber("<TWILIO FROM NUMBER>"),
//                "Your Two Factor Authentication code is: "+ twoFaCode).create();
//
//        return true;
//
//
//    }
//}