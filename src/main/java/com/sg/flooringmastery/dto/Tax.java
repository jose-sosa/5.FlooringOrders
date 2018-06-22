/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author josesosa
 */
public class Tax {

    private String state;
    private String stateAbbreviation;
    private BigDecimal taxRate;

    public Tax(String state,String stateAbbreviation ,BigDecimal taxVariable) {
        this.state = state;
        this.stateAbbreviation= stateAbbreviation;
        this.taxRate = taxVariable;
    }
    
    public String getState() {
        return state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }
    
    
    
    
    
    

}
