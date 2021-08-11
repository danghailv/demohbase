package com.example.demoHbase.repository.IRepository;

import com.example.demoHbase.dto.BookRequestDTO;
import com.example.demoHbase.model.Book;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;


@Repository
public interface IBookRepository {
    Long addBookToHbase(Book book);
    Long updateBookToHbase(Book book);
    Long deleteBookToHbase(Book book);
    List<Book> getAllBookToHbase();

}
