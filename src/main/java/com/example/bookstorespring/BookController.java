package com.example.bookstorespring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BookController {

    @GetMapping("/")
    public String homePage(Model model){
        ArrayList<BooksModel> books = GetAllBooksUseCase.getBooks();
        model.addAttribute("books", books);

        return "index";
    }
}
