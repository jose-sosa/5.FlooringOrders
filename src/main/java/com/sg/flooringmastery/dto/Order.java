/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author josesosa
 */
public class Order {
    private final int orderNumber;
    private final String date;
    private String customerName;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal taxCharged;
    private BigDecimal total;
    private String state;
    private String stateAbbreviation;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal costPSF;
    private BigDecimal laborCostPSF;
    
    public Order(int orderNumber, String date) {
        this.orderNumber = orderNumber;
        this.date = date;
    }

    public String getDate() {
        return date;
    }
   
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTaxCharged() {
        return taxCharged;
    }

    public void setTaxCharged(BigDecimal taxCharged) {
        this.taxCharged = taxCharged;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPSF() {
        return costPSF;
    }

    public void setCostPSF(BigDecimal costPSF) {
        this.costPSF = costPSF;
    }

    public BigDecimal getLaborCostPSF() {
        return laborCostPSF;
    }

    public void setLaborCostPSF(BigDecimal laborCostPSF) {
        this.laborCostPSF = laborCostPSF;
    }
    
    public void calculateOtherValues() {
        
        
        BigDecimal temp = this.area.multiply(this.costPSF);
        setMaterialCost(temp.setScale(2,RoundingMode.HALF_UP));
        
        BigDecimal temp2= this.area.multiply(this.laborCostPSF);
        setLaborCost(temp2.setScale(2,RoundingMode.HALF_UP));
        
        BigDecimal temp3 = this.materialCost.add(this.laborCost);
        setTotal(temp3.setScale(2,RoundingMode.HALF_UP));
        
        BigDecimal temp4 = this.taxRate.multiply(this.total);
        setTaxCharged(temp4.setScale(2,RoundingMode.HALF_UP));
      
    }
    

    
    @Override
    public String toString(){
        
        return  "Order details: Order Number: " +
                this.orderNumber + " | Customer Name: " +
                this.customerName + " | State Abbreviation: " +
                this.stateAbbreviation + " | State: " +
                this.state + " | Tax Rate: " +
                this.taxRate + " | Product Type: " +
                this.productType + " | Area: " +
                this.area + " square feet | Cost Per Square Foot: $" +
                this.costPSF + " | Labor Cost Per Square Foot: $" +
                this.laborCostPSF + " | Material Cost: $" +
                this.materialCost + " | Labor Cost: $" +
                this.laborCost + " | Tax Charged $" +
                this.taxCharged + " | Total: $" +
                this.total;
    }
}

