package com.springboot.books.controllers;

import com.springboot.books.dtos.BookRequestDto;
import com.springboot.books.dtos.UpdateBookRequestDto;
import com.springboot.books.models.Author;
import com.springboot.books.models.Book;
import com.springboot.books.models.Publisher;
import com.springboot.books.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }



    @GetMapping(value = "/books")
    public ResponseEntity<Page<Book>> getBooks(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(bookService.getBooks(pageable));
    }

    @GetMapping(value = "/books-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping(value = "/books-by-title/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }

    @GetMapping(value = "/books/{publisherId}")
    public ResponseEntity<Page<Book>> getBooksByPublisherId(@PathVariable Long publisherId,
                                                            @PageableDefault(size = 5) Pageable pageable){

        return ResponseEntity.ok(bookService.getBooksByPublisherId(publisherId,pageable));
    }


    @PostMapping(value = "/books")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequestDto bookRequestDto){
        Book book= new Book();
        book.setTitle(bookRequestDto.title());
        book.setEdition(bookRequestDto.edition());
        book.setPublicationYear(bookRequestDto.publicationYear());

        Long publisherId= bookRequestDto.publisherId();
        List<Long> authorsIds= bookRequestDto.authorsIds();

        Book addedBook= bookService.addBook(book,publisherId,authorsIds);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }


    @PutMapping(value = "/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody @Valid UpdateBookRequestDto updateBookRequestDto,
                                           @PathVariable Long id){
        Book book= new Book();
        BeanUtils.copyProperties(updateBookRequestDto,book);
        book.setId(id);

        return ResponseEntity.ok(bookService.updateBook(book));
    }

    @DeleteMapping(value = "/books/{id}")
    public ResponseEntity<Book> removeBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.removeBook(id));
    }


}
