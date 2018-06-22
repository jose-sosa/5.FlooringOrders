/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;

/**
 *
 * @author josesosa
 */
public interface UserIO {
    
    void print(String msg);
    

    double readDouble(String prompt) throws NumberFormatException;

    double readDouble(String prompt, double min, double max) throws NumberFormatException;


    int readInt(String prompt) throws NumberFormatException;

    int readInt(String prompt, int min, int max) throws NumberFormatException;


    String readString(String prompt);
    
    
    String readString(String prompt, String compare, String toThis);
    
    double readDouble(String prompt, double min, Double compare, String toThis) throws NumberFormatException;

    BigDecimal readBigDecimal (String prompt) throws NumberFormatException;
    
    BigDecimal readBigDecimal(String prompt, BigDecimal oldValue) throws NumberFormatException;
  
}
