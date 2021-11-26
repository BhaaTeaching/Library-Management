package com.microservice.application.jobs;

import com.microservice.application.controller.dto.request.SmsRequest;
import com.microservice.application.model.Loan;
import com.microservice.application.repositories.LoanRepository;
import com.microservice.application.services.UserService;
import com.microservice.application.services.notification.TwilioSmsSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LoanBookJob {
    private final LoanRepository loanRepository;
    private final TwilioSmsSender twilioSmsSender;
    private final UserService userService;
    public LoanBookJob(LoanRepository loanRepository, TwilioSmsSender twilioSmsSender, UserService userService) {
        this.loanRepository = loanRepository;
        this.twilioSmsSender = twilioSmsSender;
        this.userService = userService;
    }

    @Scheduled(fixedRate = 2000L)
    private void returnBookReminder() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        List<Loan> loans = loanRepository.findByReturnBookLessThan(new Date(calendar.getTimeInMillis()));
        loans.forEach(loan -> {
            SmsRequest smsRequest = new SmsRequest(userService.getPhoneNumber(loan.getUser()), "Please return book tomorrow.");
            twilioSmsSender.sendSms(smsRequest);
        });
    }
}
