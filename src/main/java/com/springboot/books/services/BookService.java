package com.springboot.books.services;

import com.springboot.books.exceptions.*;
import com.springboot.books.models.Author;
import com.springboot.books.models.Book;
import com.springboot.books.models.Publisher;
import com.springboot.books.repositories.AuthorRepository;
import com.springboot.books.repositories.BookRepository;
import com.springboot.books.repositories.PublisherRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;


    @Autowired
    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }


    public Page<Book> getBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book with id: "+id+" not found!"));
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title).orElseThrow(()-> new BookTitleNotFoundException("Book with title: "+title+" not found!"));
    }

    public Page<Book> getBooksByPublisherId(Long publisherId,Pageable pageable) {

        //Method 1 (by using the publisher after recovering it from the DB)
        Publisher publisher= publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherNotFoundException("Publisher with id: "+publisherId+" not found!"));
        return bookRepository.findByPublisher(publisher,pageable);

        //Method 2 (by using directly the publisherId)
        //return bookRepository.findByPublisherId(publisherId,pageable);
    }

    @Transactional
    public Book addBook(Book book,Long publisherId,List<Long> authorsIds) {
        if(bookRepository.existsByTitleIgnoreCase(book.getTitle())){
            throw new BookTitleExistsException("Book with title: "+book.getTitle()+" already exists!");
        }

        Publisher publisher= publisherRepository.findById(publisherId).orElseThrow(()-> new PublisherNotFoundException("Publisher with id: "+publisherId+" not found!"));

        List<Author> authors = new ArrayList<>();
        for(Long authorId : authorsIds){
            Author author=  authorRepository.findById(authorId).orElseThrow(()->new AuthorNotFoundException("Author with id: "+authorId+" not found!"));
            authors.add(author);
        }

        book.setPublisher(publisher);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Book book) {
        Book recoveredBook= getBookById(book.getId());

        if(bookTitleExistsAndBelongsToAnotherInstance(book.getTitle(),recoveredBook)){
            throw new BookTitleExistsException("Book with title: "+book.getTitle()+" already exists!");
        }

        BeanUtils.copyProperties(book,recoveredBook,"publisher","authors");
        return recoveredBook;
    }

    private boolean bookTitleExistsAndBelongsToAnotherInstance(String title,Book recoveredBook) {
        return bookRepository.existsByTitleIgnoreCase(title) && !title.equalsIgnoreCase(recoveredBook.getTitle());
    }

    @Transactional
    public Book removeBook(Long id) {
        Book book= getBookById(id);
        bookRepository.delete(book);

        book.getPublisher().getId();//Force the initialization of the associated publisher entity
        book.getAuthors().size();//Force the initialization of the lazy-loaded authors collection

        return book;
    }





}
