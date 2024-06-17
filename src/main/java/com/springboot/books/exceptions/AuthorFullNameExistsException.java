package com.springboot.books.exceptions;

public class AuthorFullNameExistsException extends RuntimeException {

    public AuthorFullNameExistsException() {
    }

    public AuthorFullNameExistsException(String message){
        super(message);
    }

}
