package com.example.demoHbase.service.impBookService;

import com.example.demoHbase.dto.BookRequestDTO;
import com.example.demoHbase.dto.mapper.MapperBook;
import com.example.demoHbase.model.Book;
import com.example.demoHbase.repository.IRepository.IBookRepository;
import com.example.demoHbase.service.IBookservice.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService {
    private final IBookRepository iBookRepository;

    @Autowired
    public BookService(IBookRepository iBookRepository){
        this.iBookRepository = iBookRepository;
    }

    @Override
    public Long addBook(BookRequestDTO bookRequestDTO) {
        Book book = MapperBook.bookRqToBook(bookRequestDTO);
        List<Book> books = findAllBook();
        for (Book book1: books
        ) {
            if(book1.getId()==book.getId()){
                return Long.valueOf(-1);
            }
        }
        return iBookRepository.addBookToHbase(book);
    }


    @Override
    public Long deleteBook(BookRequestDTO bookRequestDTO) {
        Book book = MapperBook.bookRqToBook(bookRequestDTO);
        List<Book> books = findAllBook();
        for (Book book1: books
        ) {
            if(book1.getId()==book.getId()){
                return Long.valueOf(-1);
            }
        }
        return iBookRepository.deleteBookToHbase(book);
    }

    @Override
    public List<Book> findAllBook() {

        return iBookRepository.getAllBookToHbase();
    }

    @Override
    public Long update(BookRequestDTO bookRequestDTO) {
        Book book = MapperBook.bookRqToBook(bookRequestDTO);
        List<Book> books = findAllBook();
        for (Book book1: books
             ) {
            if(book1.getId()==book.getId()){
                return Long.valueOf(-1);
            }
        }
        return iBookRepository.updateBookToHbase(book);
    }

    @Override
    public List<Book> findAllByName(String name) {
        List<Book> books =findAllBook();
        List<Book> result =new ArrayList<>();
        for (Book book:books) {
            if(book.getName().contains(name)){
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findAllByAuthor(String name) {
        List<Book> books =findAllBook();
        List<Book> result =new ArrayList<>();
        for (Book book:books) {
            if(book.getNameAuthor().contains(name)){
                result.add(book);
            }
        }
        return result;
    }
}
