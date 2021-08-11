package com.example.demoHbase.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRequestDTO {
    private long id;
    private String name;
    private int page;
    private String publish;
    private String nameAuthor;
    private String description;
}

