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
public class Product {

    private String productType;
    private BigDecimal costPSF;
    private BigDecimal laborCostPSF;
    
    public Product(String productType, String costPSF,String laborCostPSF ) {
        this.productType = productType;
        this.costPSF = new BigDecimal(costPSF);
        this.laborCostPSF = new BigDecimal(laborCostPSF);
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getCostPSF() {
        return costPSF;
    }

    public BigDecimal getLaborCostPSF() {
        return laborCostPSF;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setCostPSF(BigDecimal costPSF) {
        this.costPSF = costPSF;
    }

    public void setLaborCostPSF(BigDecimal laborCostPSF) {
        this.laborCostPSF = laborCostPSF;
    }
    
    
    
    
}
