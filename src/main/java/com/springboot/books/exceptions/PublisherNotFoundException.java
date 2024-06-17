package com.springboot.books.exceptions;

public class PublisherNotFoundException extends RuntimeException{

    public PublisherNotFoundException(){
    }

    public PublisherNotFoundException(String message){
        super(message);
    }
}
