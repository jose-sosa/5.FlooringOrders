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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryDaoStubImpl implements FlooringMasteryDao {

    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, Tax> taxes = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private final Order stubOrder;
    private final Order stubOrder2;
    private final Order stubOrder3;
    

    public FlooringMasteryDaoStubImpl() {
        this.stubOrder = new Order(1, "2001-3-20");
        this.stubOrder.setState("NY");
        this.stubOrder.setProductType("Wood");
        orders.put(1,stubOrder);
        
        
        this.stubOrder2 = new Order (2, "2000-3-20");
        this.stubOrder2.setState("HI");
        this.stubOrder2.setProductType("Granite");
        orders.put(2,stubOrder2);
        
        this.stubOrder3 = new Order (3, "2018-3-20");
        
        taxes.put("NY", null);
        products.put("Wood",null);
    
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
        return removedOrder;
    }

    @Override
    public Order editAnOrder(Order tempOrder) throws FlooringMasteryPersistenceException {

        orders.put(tempOrder.getOrderNumber(), tempOrder);
        
        return tempOrder;
        
    }

    @Override
    public Order addAnOrder(Order tempOrder) throws FlooringMasteryPersistenceException {
        orders.put(tempOrder.getOrderNumber(),tempOrder);
        // writeOrdersToFile();
        return tempOrder;
    
    }
    
    @Override
    public Order getAnOrder(int temp) throws FlooringMasteryPersistenceException {
        return orders.get(temp);
    }
    
    @Override
    public Order verifyTaxAndProduct(Order temp) throws FlooringMasteryPersistenceException {

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
        System.out.println(temp);
        return temp;
    }

    @Override
    public void writeOrdersToFile() throws FlooringMasteryPersistenceException{
        
    }

    @Override
    public void save() throws FlooringMasteryPersistenceException {
    }
    
}
