package com.springboot.books.services;

import com.springboot.books.exceptions.AuthorFullNameExistsException;
import com.springboot.books.exceptions.AuthorNotFoundException;
import com.springboot.books.exceptions.PublisherNameExistsException;
import com.springboot.books.models.Author;
import com.springboot.books.repositories.AuthorRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Page<Author> getAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException("Author with id: "+id+" not found!"));
    }

    @Transactional
    public Author addAuthor(Author author) {
        if(authorRepository.existsByFullNameIgnoreCase(author.getFullName())){
            throw new AuthorFullNameExistsException("Author with full name: "+author.getFullName()+" already exists!");
        }
        return authorRepository.save(author);
    }

    @Transactional
    public Author updateAuthor(Author author) {
        Author recoveredAuthor= getAuthorById(author.getId());

        if(authorFullNameExistsAndBelongsToAnotherInstance(author.getFullName(),recoveredAuthor)){
            throw new AuthorFullNameExistsException("Author with full name: "+author.getFullName()+" already exists!");
        }

        BeanUtils.copyProperties(author,recoveredAuthor,"books");
        return recoveredAuthor;
    }

    private boolean authorFullNameExistsAndBelongsToAnotherInstance(String fullName,Author recoveredAuthor) {
        return authorRepository.existsByFullNameIgnoreCase(fullName) && !fullName.equalsIgnoreCase(recoveredAuthor.getFullName());
    }




}
