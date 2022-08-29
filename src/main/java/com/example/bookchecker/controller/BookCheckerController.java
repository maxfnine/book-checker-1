package com.example.bookchecker.controller;

import com.example.bookchecker.model.dto.BrandModelOutput;
import com.example.bookchecker.service.BooksServiceJSONFile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class BookCheckerController {
    @GetMapping("/")
    public String getMainPage() throws IOException {
        return "index";
    }
}
