package com.example.bookstoreapi;


import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book getBookByIsbn(String isbn) {
        Book book = findByIsbn(isbn);
        if (book == null) {
            throw new EntityNotFoundException("Book not found for ISBN: " + isbn);
        }
        return book;
    }

    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
}
