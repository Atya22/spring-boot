package org.example.entrance.Service;

import org.example.entrance.Model.Book;

import java.util.Arrays;
import java.util.List;

public class BookService {
    public Integer calculateTotalPrice(List<Book> books){
//we take from the front-end list of the books and the return the total price
        var sum = 0;
        for (var b :  books){
            sum += b.getPrice();
        }
        return sum;
    }

    public List<Book> getBooks() {
//        return "my book";
        var books = Arrays.asList(
                new Book("aa", 3),
                new Book("bb", 45),
                new Book("kk", 6)
        );
        return books;
    }
}
