package com.microservice.application.repositories;

import com.microservice.application.model.DatabaseFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<DatabaseFile, Long> {
    DatabaseFile findByBookId(Long bookId);
}
