package com.springboot.books.exceptions;


public class BookTitleExistsException extends RuntimeException{

    public BookTitleExistsException(){
    }

    public BookTitleExistsException(String message){
        super(message);
    }

}
