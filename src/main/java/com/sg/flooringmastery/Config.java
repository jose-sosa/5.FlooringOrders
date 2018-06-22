/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author josesosa
 */
public class Config {
    private String mode;
    private String password;
    private static final String CONFIG = "configuration.txt";
    private static final String DELIMITER = "::";

    public Config() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {

        scanner = new Scanner(new BufferedReader(new FileReader(CONFIG)));
          
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load the vending into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            this.mode = currentTokens[0];
            this.password = currentTokens[1];
        }
        scanner.close();
        
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    
    
}
