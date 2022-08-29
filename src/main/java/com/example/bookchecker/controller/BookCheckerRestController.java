package com.example.bookchecker.controller;

import com.example.bookchecker.model.dto.BrandModelOutput;
import com.example.bookchecker.service.BooksServiceJSONFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookCheckerRestController {
    private final BooksServiceJSONFile booksService;

    public BookCheckerRestController(BooksServiceJSONFile booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/check")
    public List<BrandModelOutput> check(){
        return booksService.checkBooks();
    }
}
