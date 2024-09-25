package com.redfintek.booknetworkapi.controllers;

import com.redfintek.booknetworkapi.model.BookRequest;
import com.redfintek.booknetworkapi.model.BookResponse;
import com.redfintek.booknetworkapi.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {


    private final BookService bookService;

    @GetMapping(value = {"/books", "/books/"})
    public ResponseEntity<List<BookResponse>> getAll(){
        return ResponseEntity.ok(bookService.fetchBooks());
    }

    @PostMapping(value = {"/books", "/books/"})
    public ResponseEntity<BookResponse> addBook(@RequestBody @Valid BookRequest bookRequest){
        if(bookRequest == null || bookRequest.equals("")){
            throw new RuntimeException("Invalid Book Attributes Entered");
        }
        return ResponseEntity.ok(bookService.add(bookRequest));
    }

    @GetMapping(value = {"/books/{id}"})
    public ResponseEntity<BookResponse> viewBook(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookService.findById(id));
    }
}
