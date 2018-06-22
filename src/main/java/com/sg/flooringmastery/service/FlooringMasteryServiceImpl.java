/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryNoOrdersForTheRequestedDateException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService{
    private FlooringMasteryDao dao;
    private FlooringMasteryAuditDao auditDao;
    

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao, FlooringMasteryAuditDao auditDao) {
        this.dao = dao;  
        this.auditDao = auditDao;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringMasteryPersistenceException {
        return dao.getAllOrders();
    }

    @Override
    public void save() throws FlooringMasteryPersistenceException {
        dao.save();
    }

    @Override
    public Order addAnOrder(Order temp) throws FlooringMasteryPersistenceException, FlooringMasteryContainsInvalidInputException {
        
        return dao.addAnOrder(temp);
        
    }

    @Override //change later, so that it validates that the order exists that ask the user for input the validates that the order is of the proper format
    public Order editAnOrder(Order temp) throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException, FlooringMasteryContainsInvalidInputException {
        
        return dao.editAnOrder(temp);
    }

    @Override
    public Order removeAnOrder(int temp) throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException {
        
        Order tempOrder = dao.getAnOrder(temp);
        return dao.removeAnOrder(tempOrder);
         
    }
    @Override
    public Order validateOrderByDateAndOrderNumber(int tempOrderNumber, String tempDate) throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException{
        Order tempOrder = dao.getAnOrder(tempOrderNumber);

        if(tempOrder == null || !tempOrder.getDate().equals(tempDate)){
            throw new FlooringMasteryOrderNotFoundException("The order details provided do not correspond with an existing order");
        }
        return tempOrder;
    }

    @Override
    public List<Order> getOrderByDate(String temp) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrdersForTheRequestedDateException{
        List<Order> orderByDate = dao.getAllOrders()
                .stream()
                .filter(s -> s.getDate().equals(temp))
                .collect(Collectors.toList());
        if(orderByDate.isEmpty()){
            throw new FlooringMasteryNoOrdersForTheRequestedDateException("This date has no orders associated with it.");
        }
        
        return orderByDate;
       
    }
    
     @Override
    public List<Order> getOrderByDateAndNumber(String tempDate, int tempOrderNumber) throws FlooringMasteryPersistenceException, FlooringMasteryNoOrdersForTheRequestedDateException{
        List<Order> orderByNumber = getOrderByDate(tempDate)
                .stream()
                .filter(s -> s.getOrderNumber() == (tempOrderNumber))
                .collect(Collectors.toList());
        if(orderByNumber.isEmpty()){
            throw new FlooringMasteryNoOrdersForTheRequestedDateException("This date has no orders associated with it.");
        }
        
        return orderByNumber;
       
    }

    @Override
    public Order verifyTaxAndProduct(Order temp)throws FlooringMasteryPersistenceException,FlooringMasteryContainsInvalidInputException {
       temp =  dao.verifyTaxAndProduct(temp);
       if(temp == null){
           throw new FlooringMasteryContainsInvalidInputException(" The order contains either incorrect Tax information or incorrect Product information");
        }
        
        return temp;
    }

    @Override
    public Order getAnOrder(int temp) throws FlooringMasteryPersistenceException {
        return dao.getAnOrder(temp);
    }
    
    @Override
    public List<Order> filterByChoice(int b, String x) throws FlooringMasteryPersistenceException, NumberFormatException 
    {

        List<Order> orderByMisc = dao.getAllOrders();

            switch(b) {
                case 1:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getState().equals(x))
                        .collect(Collectors.toList()); 
                    break;
                case 2:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getArea().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 3:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getOrderNumber() == Integer.parseInt(x))
                        .collect(Collectors.toList()); 
                    break;
                case 4:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getCustomerName().equals(x))
                        .collect(Collectors.toList()); 
                    break;
                case 5:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getStateAbbreviation().equals(x))
                        .collect(Collectors.toList()); 
                    break;
                case 6:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getProductType().equals(x))
                        .collect(Collectors.toList()); 
                    break;
                case 7:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getTaxRate().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 8:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getCostPSF().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 9:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getLaborCostPSF().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 10:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getMaterialCost().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 11:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getLaborCost().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 12:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getTaxCharged().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 13:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getTotal().compareTo(new BigDecimal(x)) == 0)
                        .collect(Collectors.toList()); 
                    break;
                case 14:
                    orderByMisc = orderByMisc
                        .stream()
                        .filter(s -> s.getDate().equals(x))
                        .collect(Collectors.toList()); 
                    break;
                default:
                    System.out.println("An error has occured!");
            }
            

        return orderByMisc;
    
    }
}
// public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException {
//        loadInventory();
//        
//        return inventory.values()
//                .stream()
//                .filter(s -> s.getCost().doubleValue() <= temp.doubleValue())
//                .filter(s -> s.getInventory() > alsoTemp)
//                .collect(Collectors.toList());
//        
//    }




//        
//        List<Method> temp = null;
//        Method[] temp2 = new Method[14];
//        for(Method method : Order.class.getMethods()){
//            if(method.getName().startsWith("get")){
//                temp.add(method);
//                //method.getReturnType();
//            }
//            for(int i = 0; i<Order.class.getMethods().length;i++){
//                temp2[i] = Order.class.getMethods()[i];
//            }
//        }
//        
//        if(b == 1 || b== 4 || b== 5 || b== 6 || b== 14 ){
//           List<Order> orderByDate = dao.getAllOrders()
//            .stream()
//            .filter(s -> s.temp2[b].getName().equals(x))
//            .collect(Collectors.toList()); 
//        }else if(b == 3){
//            List<Order> orderByDate = dao.getAllOrders()
//            .stream()
//            .filter(s -> s.temp[b] == x )
//            .collect(Collectors.toList());
//        }else{
//            List<Order> orderByDate = dao.getAllOrders()
//            .stream()
//            .filter(s -> s.temp[b].comaresTo(x) == 0 )
//            .collect(Collectors.toList());
//        }