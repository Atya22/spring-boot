package com.atl.idk;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String name;
    private Double price;
    private Genre genre;
    //    get endpoint, return list of books
//    mockbook in util (method that will return list of books
}
