package com.springboot.books.exceptions;

public class PublisherEmailExistsException extends RuntimeException{

    public PublisherEmailExistsException(){
    }

    public PublisherEmailExistsException(String message){
        super(message);
    }

}
