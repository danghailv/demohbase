package com.example.demoHbase.dto.mapper;

import com.example.demoHbase.dto.BookRequestDTO;
import com.example.demoHbase.model.Book;

public class MapperBook {
    public static Book bookRqToBook(BookRequestDTO bookRequestDTO){
        Book book = new Book();
        book.setId(bookRequestDTO.getId());
        book.setName(bookRequestDTO.getName());
        book.setDescription(bookRequestDTO.getDescription());
        book.setPage(bookRequestDTO.getPage());
        book.setNameAuthor(bookRequestDTO.getNameAuthor());
        book.setPublish(book.getPublish());
        book.setEdit(java.time.LocalDate.now().toString());
        return book;
    }
}
