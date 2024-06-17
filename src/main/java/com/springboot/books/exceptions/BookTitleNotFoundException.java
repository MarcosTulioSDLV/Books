package com.springboot.books.exceptions;

public class BookTitleNotFoundException extends RuntimeException{

    public BookTitleNotFoundException(){
    }

    public BookTitleNotFoundException(String message){
        super(message);
    }

}
