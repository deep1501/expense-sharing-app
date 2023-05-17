package com.springboot.expensesharingapp.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException(){
        super("Resource not found on server !");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
