package com.atl.idk.util;

import com.atl.idk.Book;

import java.util.Arrays;
import java.util.List;

public class MockBook {
    public List<Book> getBooks() {
        var books = Arrays.asList(
                new Book("aa", 3),
                new Book("bb", 45.),
                new Book("kk", 6)
        );
        return books;
    }
}
