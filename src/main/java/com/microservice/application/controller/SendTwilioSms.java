package com.microservice.application.controller;

import com.microservice.application.controller.dto.request.SmsRequest;
import com.microservice.application.services.notification.SmsSender;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class SendTwilioSms {

    private final SmsSender sendSms;

    @Autowired
    public SendTwilioSms(SmsSender sendSms) {
        this.sendSms = sendSms;
    }

    @PostMapping("/send-sms")
    public Message sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        return sendSms.sendSms(smsRequest);
    }
}
