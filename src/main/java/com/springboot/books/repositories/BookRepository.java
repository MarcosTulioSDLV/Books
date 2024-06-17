package com.springboot.books.repositories;

import com.springboot.books.models.Book;
import com.springboot.books.models.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    boolean existsByTitleIgnoreCase(String title);

    Optional<Book> findByTitleIgnoreCase(String title);

    //--
    //Method 1 (by using the publisher after recovering it from the DB)
    Page<Book> findByPublisher(Publisher publisher,Pageable pageable);

    //Method 2 (by using directly the publisherId)
    Page<Book> findByPublisherId(Long publisherId,Pageable pageable);

    //--

}
