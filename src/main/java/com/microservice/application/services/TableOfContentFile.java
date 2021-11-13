package com.microservice.application.services;

import com.microservice.application.model.Book;
import com.microservice.application.model.DatabaseFile;
import com.microservice.application.repositories.BookRepository;
import com.microservice.application.repositories.FileRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TableOfContentFile implements FilesService {

    private final FileRepository fileRepository;
    private final GetObjectsService getObjectsService;

    public TableOfContentFile(FileRepository fileRepository, GetObjectsService getObjectsService) {
        this.fileRepository = fileRepository;
        this.getObjectsService = getObjectsService;
    }

    @Override
    public DatabaseFile saveFile(MultipartFile file, Long bookId) throws NotFoundException, IOException {
        String fileName = file.getOriginalFilename();
        Book book = getObjectsService.getBookById(bookId);

        DatabaseFile doc = new DatabaseFile(fileName, file.getContentType(), file.getBytes(), book);
        return fileRepository.save(doc);
    }

    @Override
    public DatabaseFile getFileByBookId(Long fileId) {
        return fileRepository.findByBookId(fileId);
    }

    @Override
    public List<DatabaseFile> getFiles() {
        return (List<DatabaseFile>) fileRepository.findAll();
    }
}