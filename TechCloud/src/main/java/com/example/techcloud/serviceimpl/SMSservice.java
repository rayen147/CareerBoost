package com.example.techcloud.serviceimpl;

import com.example.techcloud.serviceinterface.ISMSService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

@Slf4j
public class SMSservice implements ISMSService {
    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth-token}")
    private String AUTH_TOKEN;

    @Value("${twilio.phone-number}")
    private String FROM_NUMBER;

    @Override
    public void sendSms(String to, String message) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                new PhoneNumber("+21629239275"), // To number
                new PhoneNumber(FROM_NUMBER), // From number
                message // SMS body
        ).create();
    }

}
