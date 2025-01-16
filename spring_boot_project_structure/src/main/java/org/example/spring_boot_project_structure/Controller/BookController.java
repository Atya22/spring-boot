package org.example.spring_boot_project_structure.Controller;


import org.example.spring_boot_project_structure.Model.Book;
import org.example.spring_boot_project_structure.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    private static BookService bookService;

    //    @GetMapping - front-end ucun get request yaziriq
    @GetMapping("list")
    public List<?> getBooks() {
        return bookService.getBooks();
    }

    //this part in the above is an end-point, we write end-point inside of the controller
    @PostMapping("price")
    public Integer totalPrice(@RequestBody List<Book> books) {
        return bookService.calculateTotalPrice(books);
    }

}
