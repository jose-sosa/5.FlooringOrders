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
import java.util.List;

/**
 *
 * @author josesosa
 */
public interface FlooringMasteryDao {
    public List<Order> getAllOrders()throws FlooringMasteryPersistenceException;
    public ArrayList<Product> getAllProducts() throws FlooringMasteryPersistenceException;
    public ArrayList<Tax> getAllTaxes() throws FlooringMasteryPersistenceException;
//    public List<Order> sortOrderByInput(BigDecimal temp, int alsoTemp) ;
    public void writeOrdersToFile() throws FlooringMasteryPersistenceException;
    public Order removeAnOrder(Order temp)  throws FlooringMasteryPersistenceException;
    public Order editAnOrder(Order tempOrder)  throws FlooringMasteryPersistenceException;
    public Order addAnOrder(Order tempOrder)  throws FlooringMasteryPersistenceException;

    public Order getAnOrder(int temp) throws FlooringMasteryPersistenceException;

    public Order verifyTaxAndProduct(Order temp)throws FlooringMasteryPersistenceException;

    public void save() throws FlooringMasteryPersistenceException;
     
}

