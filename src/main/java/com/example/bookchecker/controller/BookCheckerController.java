package com.example.bookchecker.controller;

import com.example.bookchecker.service.BooksServiceJSONFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "index";
    }

    @GetMapping("/showstatus")
    public String showStatus(Model model){
        model.addAttribute("brands",booksService.checkBooks());
        return "showStatus";
    }
}
