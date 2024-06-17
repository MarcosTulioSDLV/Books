package com.springboot.books.exceptions;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(){
    }

    public AuthorNotFoundException(String message){
        super(message);
    }

}
