package com.microservice.application.controller;

import com.microservice.application.exception.ValidationException;
import com.microservice.application.services.books.LoanBookService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class LoanBookController extends BaseController {

    private final LoanBookService loanBookService;

    public LoanBookController(LoanBookService loanBookService) {
        this.loanBookService = loanBookService;
    }


    @PostMapping("loan-book")
    public ResponseEntity<Object> loan(@RequestParam Long bookId, @RequestParam Long userId) throws NotFoundException, ValidationException {
        return responseEntity(loanBookService.loan(bookId, userId));
    }

    @PostMapping("extend-loan-book")
    public ResponseEntity<Object> extendLoan(@RequestParam Long loanId) throws NotFoundException, ValidationException {
        return responseEntity(loanBookService.extendLoan(loanId));
    }
}
