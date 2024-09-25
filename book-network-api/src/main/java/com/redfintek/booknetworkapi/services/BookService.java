package com.redfintek.booknetworkapi.services;

import com.redfintek.booknetworkapi.model.Book;
import com.redfintek.booknetworkapi.model.BookRequest;
import com.redfintek.booknetworkapi.model.BookResponse;
import com.redfintek.booknetworkapi.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> fetchBooks(){
        List<Book> books = bookRepository.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        if(!books.isEmpty()) {
            books.forEach(b -> bookResponses.add(new BookResponse(b.getTitle(), b.getIsbn(), b.getAuthors())));
            return bookResponses;
        }
        throw new EntityNotFoundException("No Books found");
    }

    public BookResponse findById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return new BookResponse(book.getTitle(), book.getIsbn(), book.getAuthors());
    }

    public BookResponse add(BookRequest bookRequest){
        Book book = new Book();
        book.setTitle(bookRequest.title());
        book.setIsbn(bookRequest.isbn());
        book.setAuthors(bookRequest.authors());
        bookRepository.save(book);

        return new BookResponse(book.getTitle(), book.getIsbn(), book.getAuthors());
    }
    //todo - implement amend book
    //todo - implement delete book
}
