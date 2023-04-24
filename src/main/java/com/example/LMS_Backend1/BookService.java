package com.example.LMS_Backend1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public void checkOutBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            bookRepository.save(book);
        }
    }

    public void returnBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            bookRepository.save(book);
        }
    }
}
