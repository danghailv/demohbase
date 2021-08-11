package com.example.demoHbase.service.IBookservice;

import com.example.demoHbase.dto.BookRequestDTO;
import com.example.demoHbase.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookService {
    Long addBook(BookRequestDTO bookRequestDTO);
    Long deleteBook(BookRequestDTO bookRequestDTO);
    List<Book> findAllBook();
    Long update(BookRequestDTO bookRequestDTO);
    List<Book> findAllByName(String name);
    List<Book> findAllByAuthor(String name);

}
