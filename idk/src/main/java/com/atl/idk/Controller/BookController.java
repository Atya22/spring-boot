package com.atl.idk.Controller;

import com.atl.idk.util.MockBook;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class BookController {
    private static

    @GetMapping("list")
    public List<?> getBooks() {
        return MockBook.getBooks();
    }
}
