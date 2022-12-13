package com.example.bookstorespring.controllers;

import com.example.bookstorespring.models.BookModel;
import com.example.bookstorespring.models.CartBookModel;
import com.example.bookstorespring.models.EditBookModel;
import com.example.bookstorespring.services.*;
import com.example.bookstorespring.models.BooksModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Book;
import java.util.ArrayList;

@Controller
public class BookController {

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort){
        model.addAttribute("cartBook", new CartBookModel());

        if(model.getAttribute("books") ==  null) {
            ArrayList<BooksModel> books = GetAllBooksUseCase.getBooks(sort);
            model.addAttribute("books", books);

        }

        return "index";
    }


    @GetMapping("/sorting")
    public String sorted(Model model, @RequestParam(value = "sort") String sort){
        ArrayList<BooksModel> books = GetAllBooksUseCase.getBooks(sort);
        model.addAttribute("books", books);

        return "index";
    }

    @GetMapping("/book/")
    @ResponseBody
    public BookModel bookPage(@RequestParam("bookID") String bookID){
        BookModel book = GetBookDetailsUseCase.bookDetails(bookID);

        return book;
    }

    @GetMapping("/selectBookByTitle")
    public String bookByTitle(Model model, @RequestParam String title){
        ArrayList<BooksModel> books = SerchBookUseCase.getBookByTitle(title);
        model.addAttribute("books", books);

        return "index";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(@RequestParam String bookID){
        DeleteBookFromStoreUseCase.deleteBook(bookID);

        return "redirect:/";
    }

    @GetMapping("/editBook")
    public String editBookData(Model model, @RequestParam String bookID){
        EditBookModel editBookModel = GetBookDetailsUseCase.bookDetailsToEdit(bookID);
        model.addAttribute("book", editBookModel);

        return "bookForm";
    }

    @PostMapping("/editBookDetails")
    public String editDetails(@Valid @ModelAttribute("book") EditBookModel book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);

            return "bookForm";
        }

        EditBookDataUseCase.editBookDetails(book);

        return "redirect:/";
    }
}
