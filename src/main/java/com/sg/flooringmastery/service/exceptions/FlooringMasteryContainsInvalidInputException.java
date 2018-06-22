/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service.exceptions;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryContainsInvalidInputException extends Exception {
    
    public FlooringMasteryContainsInvalidInputException(String message) {
        super(message);
    }

    public FlooringMasteryContainsInvalidInputException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
     @Override
    public String toString(){
        return "| Contains Invalid Input";
    }
   
    
}
