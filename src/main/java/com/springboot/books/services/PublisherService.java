package com.springboot.books.services;

import com.springboot.books.exceptions.PublisherEmailExistsException;
import com.springboot.books.exceptions.PublisherNameExistsException;
import com.springboot.books.exceptions.PublisherNotFoundException;
import com.springboot.books.models.Publisher;
import com.springboot.books.repositories.PublisherRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PublisherService {


    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Page<Publisher> getPublishers(Pageable pageable) {
        return publisherRepository.findAll(pageable);
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(()-> new PublisherNotFoundException("Publisher with id: "+id+" not found exception"));
    }

    @Transactional
    public Publisher addPublisher(Publisher publisher) {
        if(publisherRepository.existsByNameIgnoreCase(publisher.getName())){
            throw new PublisherNameExistsException("Publisher with name: "+publisher.getName()+" already exists!");
        }
        if(publisherRepository.existsByEmailIgnoreCase(publisher.getEmail())){
            throw new PublisherEmailExistsException("Publisher with email: "+publisher.getEmail()+" already exists!");
        }
        return publisherRepository.save(publisher);
    }

    @Transactional
    public Publisher updatePublisher(Publisher publisher) {
        Publisher recoveredPublisher= getPublisherById(publisher.getId());

        if(publisherNameExistsAndBelongsToAnotherInstance(publisher.getName(),recoveredPublisher)){
            throw new PublisherNameExistsException("Publisher with name: "+publisher.getName()+" already exists!");
        }
        if(publisherEmailExistsAndBelongsToAnotherInstance(publisher.getEmail(),recoveredPublisher)){
            throw new PublisherEmailExistsException("Publisher with email: "+publisher.getEmail()+" already exists!");
        }

        BeanUtils.copyProperties(publisher,recoveredPublisher,"books");
        return publisher;
    }

    private boolean publisherNameExistsAndBelongsToAnotherInstance(String name,Publisher recoveredPublisher) {
        return publisherRepository.existsByNameIgnoreCase(name) && !name.equalsIgnoreCase(recoveredPublisher.getName());
    }

    private boolean publisherEmailExistsAndBelongsToAnotherInstance(String email,Publisher recoveredPublisher) {
        return publisherRepository.existsByEmailIgnoreCase(email) && !email.equalsIgnoreCase(recoveredPublisher.getEmail());
    }



}
