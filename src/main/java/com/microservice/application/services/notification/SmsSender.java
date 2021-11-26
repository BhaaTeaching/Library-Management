package com.microservice.application.services.notification;

import com.microservice.application.controller.dto.request.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;

public interface SmsSender {
    Message sendSms(SmsRequest smsRequest);
}
