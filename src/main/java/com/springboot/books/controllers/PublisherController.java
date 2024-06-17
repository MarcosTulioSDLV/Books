package com.springboot.books.controllers;

import com.springboot.books.dtos.PublisherRequestDto;
import com.springboot.books.models.Publisher;
import com.springboot.books.services.PublisherService;
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
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @GetMapping(value = "/publishers")
    public ResponseEntity<Page<Publisher>> getPublishers(@PageableDefault(size = 3) Pageable pageable){
        return ResponseEntity.ok(publisherService.getPublishers(pageable));
    }

    @GetMapping(value = "/publishers/{id}")
    public ResponseEntity<Publisher> getPublisherById(Long id){
        return ResponseEntity.ok(publisherService.getPublisherById(id));
    }

    @PostMapping(value = "/publishers")
    public ResponseEntity<Publisher> addPublisher(@RequestBody @Valid PublisherRequestDto publisherRequestDto){
        Publisher publisher= new Publisher();
        BeanUtils.copyProperties(publisherRequestDto,publisher);

        Publisher addedPublisher= publisherService.addPublisher(publisher);
        return new ResponseEntity<>(addedPublisher, HttpStatus.CREATED);
    }

    @PutMapping(value = "/publishers/{id}")
    public ResponseEntity<Publisher> updatePublisher(@RequestBody @Valid PublisherRequestDto publisherRequestDto,
                                                     @PathVariable Long id){
        Publisher publisher= new Publisher();
        BeanUtils.copyProperties(publisherRequestDto,publisher);
        publisher.setId(id);

        Publisher updatedPublisher= publisherService.updatePublisher(publisher);
        return ResponseEntity.ok(updatedPublisher);
    }



}


