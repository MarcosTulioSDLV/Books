package com.springboot.books.exceptions;

public class PublisherNameExistsException extends RuntimeException{

    public PublisherNameExistsException(){
    }

    public PublisherNameExistsException(String message){
        super(message);
    }

}
