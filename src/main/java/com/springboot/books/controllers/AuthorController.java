package com.springboot.books.controllers;

import com.springboot.books.dtos.AuthorRequestDto;
import com.springboot.books.dtos.PublisherRequestDto;
import com.springboot.books.models.Author;
import com.springboot.books.models.Publisher;
import com.springboot.books.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {

    private final AuthorService authorService;


    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(value = "/authors")
    public ResponseEntity<Page<Author>> getAuthors(@PageableDefault(size = 3) Pageable pageable){
        return ResponseEntity.ok(authorService.getAuthors(pageable));
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Author> getAuthorById(Long id){
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto){
        Author author= new Author();
        BeanUtils.copyProperties(authorRequestDto,author);

        Author addedAuthor= authorService.addAuthor(author);
        return new ResponseEntity<>(addedAuthor,HttpStatus.CREATED);
    }


    @PutMapping(value = "/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody @Valid AuthorRequestDto authorRequestDto,
                                               @PathVariable Long id){
        Author author= new Author();
        BeanUtils.copyProperties(authorRequestDto,author);
        author.setId(id);

        Author updatedAuthor= authorService.updateAuthor(author);
        return ResponseEntity.ok(updatedAuthor);
    }



}
