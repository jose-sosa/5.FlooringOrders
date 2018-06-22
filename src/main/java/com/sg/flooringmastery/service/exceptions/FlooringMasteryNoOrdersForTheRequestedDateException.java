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
public class FlooringMasteryNoOrdersForTheRequestedDateException extends Exception {
    public FlooringMasteryNoOrdersForTheRequestedDateException(String message) {
        super(message);
    }

    public FlooringMasteryNoOrdersForTheRequestedDateException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
     @Override
    public String toString(){
        return "| No Order Found for the Requested Date";
    }
    
}
