package com.example.bookchecker.controller;

import com.example.bookchecker.service.BooksServiceJSONFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class BookCheckerController {
    private final BooksServiceJSONFile booksService;

    public BookCheckerController(BooksServiceJSONFile booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/")
    public String getMainPage() throws IOException {
        booksService.checkBooks();
        return "index";
    }
}
