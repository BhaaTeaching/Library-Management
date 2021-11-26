package com.microservice.application.repositories;

import com.microservice.application.model.Loan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends CrudRepository<Loan, Long> {

    @Query("SELECT MIN(returnBook) FROM Loan WHERE book_id = :bookId")
    Date findTopWithBookId(@Param("bookId") Long bookId);

    List<Loan> findByReturnBookLessThan(Date date);
}
