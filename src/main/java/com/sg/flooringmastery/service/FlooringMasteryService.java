/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryNoOrdersForTheRequestedDateException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import java.util.List;

/**
 *
 * @author josesosa
 */
public interface FlooringMasteryService {

    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException;

    public void save()throws FlooringMasteryPersistenceException;

    public Order addAnOrder(Order temp) throws FlooringMasteryPersistenceException, FlooringMasteryContainsInvalidInputException;
    
    public Order getAnOrder(int temp) throws FlooringMasteryPersistenceException;

    public Order editAnOrder(Order order)throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException, FlooringMasteryContainsInvalidInputException;

    public Order removeAnOrder(int temp)throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException;

    public List<Order> getOrderByDate(String temp) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrdersForTheRequestedDateException;

    public Order verifyTaxAndProduct(Order temp)throws FlooringMasteryPersistenceException,FlooringMasteryContainsInvalidInputException ;

    public Order validateOrderByDateAndOrderNumber(int tempOrderNumber, String tempDate)throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException;

    public List<Order> getOrderByDateAndNumber(String tempDate, int tempOrderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrdersForTheRequestedDateException;
    
    public List<Order> filterByChoice(int a, String b) throws FlooringMasteryPersistenceException; 
    
}
