package com.microservice.application.services.books;

import com.microservice.application.model.DatabaseFile;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FilesService {
    DatabaseFile saveFile(MultipartFile file, Long bookId) throws NotFoundException, IOException;

    DatabaseFile getFileByBookId(Long fileId);

    List<DatabaseFile> getFiles();

}
