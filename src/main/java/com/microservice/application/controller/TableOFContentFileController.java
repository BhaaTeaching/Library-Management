package com.microservice.application.controller;

import com.microservice.application.model.DatabaseFile;
import com.microservice.application.services.books.FilesService;
import javassist.NotFoundException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class TableOFContentFileController extends BaseController{
    private final FilesService filesService;


    public TableOFContentFileController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/get-table-of-content")
    public String get(Model model) {
        List<DatabaseFile> docs = filesService.getFiles();
        model.addAttribute("docs", docs);
        return "doc";
    }

    @PostMapping("/upload-table-of-content/{bookId}")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestBody MultipartFile tableOfContent, @PathVariable Long bookId) throws NotFoundException, IOException {
          return responseEntity(filesService.saveFile(tableOfContent, bookId));
    }
    @GetMapping("/download-table-of-content/{bookId}")
    public ResponseEntity<Object> downloadTableOfContentFile(@PathVariable Long bookId){
        DatabaseFile doc = filesService.getFileByBookId(bookId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }
}
