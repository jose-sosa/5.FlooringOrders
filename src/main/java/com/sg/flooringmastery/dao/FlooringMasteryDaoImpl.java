/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryDaoImpl implements FlooringMasteryDao{
    public static final String ORDER_FILE = "orders.txt";
    public static final String TAX_FILE = "tax.txt";
    public static final String PRODUCT_FILE = "product.txt";
    public static final String DELIMITER = "::";
    protected Map<Integer, Order> orders = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    public FlooringMasteryDaoImpl() throws FlooringMasteryPersistenceException {
        loadAllOrders();
        loadAllProducts();
        loadAllTaxes();
    }
    
    
    
    
    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
     
        
        return new ArrayList<>(orders.values());
    }
    
    @Override
    public ArrayList<Product> getAllProducts() throws FlooringMasteryPersistenceException {
       
        
        return new ArrayList<>(products.values());
    }
    
    @Override
    public ArrayList<Tax> getAllTaxes() throws FlooringMasteryPersistenceException {
        
        
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Order removeAnOrder(Order removedOrder) throws FlooringMasteryPersistenceException {
        
        orders.remove(removedOrder.getOrderNumber());
        //writeOrdersToFile();
        return removedOrder;
    }

    @Override
    public Order editAnOrder(Order tempOrder) throws FlooringMasteryPersistenceException {
        
        
        orders.put(tempOrder.getOrderNumber(), tempOrder);
        
        return tempOrder;
        
    }

    @Override
    public Order addAnOrder(Order tempOrder) throws FlooringMasteryPersistenceException {
        loadAllOrders();
        orders.put(tempOrder.getOrderNumber(),tempOrder);
        // writeOrdersToFile();
        return tempOrder;
    
    }
    
    @Override
    public Order getAnOrder(int temp) throws FlooringMasteryPersistenceException {
        
        Order temporary = orders.get(temp);
        return temporary;
    }
    
    @Override
    public Order verifyTaxAndProduct(Order temp) throws FlooringMasteryPersistenceException {
    
//        
//        System.out.println(temp.getStateAbbreviation());
//        System.out.println(temp.getProductType());
        
//        if(taxes.keySet().contains(temp.getStateAbbreviation())){
//            System.out.println("The Problem Is not in taxes");
//        }
//        
//        if(products.keySet().contains(temp.getProductType())){
//            System.out.println("The Problem Is not in products");
//        }
        
        if(taxes.keySet().contains(temp.getStateAbbreviation()) && products.keySet().contains(temp.getProductType())){
            Tax temporaryTax = taxes.get(temp.getStateAbbreviation());
            Product temporaryProduct = products.get(temp.getProductType());
            temp.setState(temporaryTax.getState());
            temp.setTaxRate(temporaryTax.getTaxRate());
            temp.setCostPSF(temporaryProduct.getCostPSF());
            temp.setLaborCostPSF(temporaryProduct.getLaborCostPSF());
        }else{
            temp = null;
        }
        
        return temp;
    }

    private void loadAllOrders() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(ORDER_FILE)));
          
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load the orders into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        // creates the hashmap with all
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]),currentTokens[13]);
            currentOrder.setCustomerName((currentTokens[1]));
            currentOrder.setStateAbbreviation((currentTokens[2]));
            currentOrder.setState((currentTokens[3]));
            currentOrder.setTaxRate((new BigDecimal(currentTokens[4])));
            currentOrder.setProductType((currentTokens[5]));
            currentOrder.setArea(new BigDecimal(currentTokens[6]));
            currentOrder.setCostPSF(new BigDecimal((currentTokens[7]) ));
            currentOrder.setLaborCostPSF(new BigDecimal((currentTokens[8])));
            currentOrder.setMaterialCost((new BigDecimal(currentTokens[9])));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[10]));
            currentOrder.setTaxCharged(new BigDecimal((currentTokens[11]) ));
            currentOrder.setTotal(new BigDecimal((currentTokens[12])));
            
            
            orders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        
        scanner.close();
    }
    
    private void loadAllProducts() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
          
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load the products into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        // creates the hashmap with all
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Product currentProduct = new Product(currentTokens[0],(currentTokens[1]),(currentTokens[2]));
            
            products.put(currentProduct.getProductType(), currentProduct);
        }
        
        scanner.close();
    }

    private void loadAllTaxes() throws FlooringMasteryPersistenceException {
        Scanner scanner;
        
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)));
          
        } catch (FileNotFoundException e) {
            throw new FlooringMasteryPersistenceException("Could not load the taxes into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            
            Tax currentTax = new Tax(currentTokens[1],currentTokens[0],new BigDecimal(currentTokens[2]));
            
            taxes.put(currentTax.getStateAbbreviation(), currentTax);
        }
        
        scanner.close();
    }

    @Override
    public void writeOrdersToFile() throws FlooringMasteryPersistenceException{
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch(IOException e) {
                throw new FlooringMasteryPersistenceException("Could not save order data.", e);
        }
        
        List<Order> allOrders =  this.getAllOrders();
        
        for(Order order: allOrders) {
            out.println(
            order.getOrderNumber() + DELIMITER + 
            order.getCustomerName() + DELIMITER + 
            order.getStateAbbreviation() + DELIMITER + 
            order.getState() + DELIMITER + 
            order.getTaxRate() + DELIMITER + 
            order.getProductType() + DELIMITER + 
            order.getArea() + DELIMITER + 
            order.getCostPSF() + DELIMITER + 
            order.getLaborCostPSF() + DELIMITER + 
            order.getMaterialCost() + DELIMITER + 
            order.getLaborCost() + DELIMITER + 
            order.getTaxCharged() + DELIMITER + 
            order.getTotal() + DELIMITER +
            order.getDate());
            out.flush();
        }
        
        out.close();
    
    }

    @Override
    public void save() throws FlooringMasteryPersistenceException {
        writeOrdersToFile();
        
    }
    
    

}

//------------------------------------------------------------------------------
  
    