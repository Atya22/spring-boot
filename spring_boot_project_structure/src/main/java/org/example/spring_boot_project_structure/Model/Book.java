package org.example.spring_boot_project_structure.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private String name;
    private Integer price;
}
