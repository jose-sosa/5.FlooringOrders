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
public class FlooringMasteryOrderNotFoundException extends Exception{
    public FlooringMasteryOrderNotFoundException(String message) {
        super(message);
    }

    public FlooringMasteryOrderNotFoundException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
     @Override
    public String toString(){
        return "| Order Not Found";
    }
}
