package com.example.bookstorespring.controllers;

import com.example.bookstorespring.models.BookModel;
import com.example.bookstorespring.models.CartBookModel;
import com.example.bookstorespring.services.GetAllBooksUseCase;
import com.example.bookstorespring.models.BooksModel;
import com.example.bookstorespring.services.GetBookDetailsUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class BookController {

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort){
        model.addAttribute("cartBook", new CartBookModel());

        if(model.getAttribute("books") == null) {
            ArrayList<BooksModel> books = GetAllBooksUseCase.getBooks(sort);
            model.addAttribute("books", books);

        }

        return "index";
    }

    @GetMapping("/sorting")
    public String sorted(Model model, @RequestParam(value = "sort") String sort){
        ArrayList<BooksModel> books = GetAllBooksUseCase.getBooks(sort);
        model.addAttribute("books", books);

        return "redirect:/";
    }

    @GetMapping("/book/")
    @ResponseBody
    public BookModel bookPage(@RequestParam("bookID") String bookID){
        BookModel book = GetBookDetailsUseCase.bookDetails(bookID);

        return book;
    }
}
