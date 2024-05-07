package com.example.techcloudpi.ServiceIMPL;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {

    @Value("AC719b0dd92b761d9e48e10cb9f2e85ec7")
    private String ACCOUNT_SID;

    @Value("6ed9a430ac187377f9bd6f90e59620bb")
    private String AUTH_TOKEN;

    @Value("+12568183848")
    private String FROM_NUMBER;

    public void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber("+21654441354"), // To number
                new PhoneNumber(FROM_NUMBER), // From number
                message).create();
    }
}
