package com.example.techcloudpi.Controller;

import com.example.techcloudpi.ServiceIMPL.SmsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SmsController {
    SmsService smsService;
    @GetMapping("/Sms")

    public void sendSms(String to, String message) {
        smsService.sendSms(to, "Votre reclamation a été bien recue ");
    }
}
