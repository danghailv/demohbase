package com.example.demoHbase.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id ;
    private String name;
    private int page;
    private String  publish;
    private String nameAuthor;
    private String description;
    private String edit;


}
