package com.example.LMS_Backend1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/")
    public Book saveBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PostMapping("/{id}/checkout")
    public ResponseEntity<String> checkOutBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else if (!book.isAvailable()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The book with ID " + id + " is not available");
        } else {
            book.setAvailable(false);
            bookService.saveBook(book);
            return ResponseEntity.ok("The book with ID " + id + " has been checked out");
        }
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        } else if (book.isAvailable()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("The book with ID " + id + " is already available");
        } else {
            book.setAvailable(true);
            bookService.saveBook(book);
            return ResponseEntity.ok("The book with ID " + id + " has been returned");
        }
    }
}
