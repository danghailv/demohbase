package com.example.demoHbase.controller;



import com.example.demoHbase.dto.BookRequestDTO;
import com.example.demoHbase.model.Book;
import com.example.demoHbase.service.IBookservice.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookContreller {
    private final IBookService iBookService;

    @Autowired
    public BookContreller(IBookService iBookService) {
        this.iBookService = iBookService;
    }


    @GetMapping()
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(iBookService.findAllBook());
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteBook(@RequestBody BookRequestDTO bookRequestDTO){
        Long check=iBookService.deleteBook(bookRequestDTO);
        if(check>0){
            return ResponseEntity.ok("Xóa thành công!");
        }
        return ResponseEntity.ok("Xóa thất bại!");
    }

    @PutMapping("{id}")
    public ResponseEntity updateBook(@RequestBody BookRequestDTO bookRequestDTO){
        Long check=iBookService.update(bookRequestDTO);
        if(check>0){
            return ResponseEntity.ok("Cập nhập thành công!");
        }
        return ResponseEntity.ok("Cập nhập thất bại!");
    }
    @GetMapping("/searchname")
    public ResponseEntity<?> searchBookByName(@RequestParam( name = "name",required = false ,defaultValue = "") String keyword){
        return  ResponseEntity.ok(iBookService.findAllByName(keyword));
    }
    @GetMapping("/searchauthor")
    public ResponseEntity<?> searchBookByAuthor(@RequestParam( name = "name",required = false ,defaultValue = "") String keyword){
        return  ResponseEntity.ok(iBookService.findAllByAuthor(keyword));
    }

}
