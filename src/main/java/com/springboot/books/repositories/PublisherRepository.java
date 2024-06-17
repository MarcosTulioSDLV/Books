package com.springboot.books.repositories;

import com.springboot.books.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByEmailIgnoreCase(String email);

}
