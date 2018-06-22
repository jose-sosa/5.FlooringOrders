/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.service.FlooringMasteryService;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryNoOrdersForTheRequestedDateException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import com.sg.flooringmastery.ui.FlooringMasteryView;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryController {
     private FlooringMasteryService service;
    private FlooringMasteryView view;
    
    
    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }
    
    
    // note in the future I may want to pass the run method an instance of Config... that would allow give me editing capabilities
    public void run(String password, String mode) throws FlooringMasteryPersistenceException, FlooringMasteryContainsInvalidInputException, FlooringMasteryOrderNotFoundException, FlooringMasteryNoOrdersForTheRequestedDateException {

        view.sayHello();
        
        boolean keepGoing = true;
        
      
            do {
                if(mode.equals("trainingController")){
                    view.displayString("\n You are currently in training mode. Saving in training mode will not persist data.");
                } 
                
                int userChoice =  view.getMenuSelection();
                
                switch(userChoice) {
                    case 0:
                        //keepGoing = runAdminControls();
                        displayOrderByInput();
                        break;
                    case 1:
                        displayOrders(); // displayOrder method in your           view.displayOrdersList < - controller... service -> dao .
                        break;
                    case 2:
                        addAnOrder();   
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        removeAnOrder();
                        break;
                    case 5:
                        save();
                        break;
                    case 6:
                        keepGoing = endProgram();
                        
                        break;
                    default:
                        System.out.println("An error has occured!");
                }

            } while(keepGoing);
    
    }
    //     VIEW <- CONTROLLER -> SERVICE    ->   DAO
    private void displayOrders() throws FlooringMasteryPersistenceException, FlooringMasteryNoOrdersForTheRequestedDateException {
        String temp = view.getOrderDate();
        try{
            view.displayOrderList(service.getOrderByDate(temp));
        }catch(FlooringMasteryNoOrdersForTheRequestedDateException e){
            view.printErrorMessage(e.getMessage());
        }
        
    } 

    private void addAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryContainsInvalidInputException{
        
        Order temp = getOrder();
        
        try{
            temp = service.verifyTaxAndProduct(temp);
            temp.calculateOtherValues();
            int keepOrThrowOut = view.displayOrderAndGetCommitment(temp);
            
            if(keepOrThrowOut == 1){
                service.addAnOrder(temp);
            }else{
                view.displayString("Your order will be discarded;");
            }
        }catch(FlooringMasteryContainsInvalidInputException e){
            view.printErrorMessage(e.getMessage());
        }
        
    }

    private void editAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException, FlooringMasteryContainsInvalidInputException, FlooringMasteryNoOrdersForTheRequestedDateException{
        String tempDate = view.getOrderDate();
        int tempOrderNumber = view.getOrderNumber();
        try{
            Order placeHolder = service.validateOrderByDateAndOrderNumber(tempOrderNumber, tempDate);
            view.displayOrderList(service.getOrderByDateAndNumber(tempDate, tempOrderNumber));
            
            Order tempOrder = view.getOrderForEditing(placeHolder);
            
            int keepOrThrowOut = view.displayOrderAndGetCommitment(tempOrder);
            
            if(keepOrThrowOut == 1){
                service.verifyTaxAndProduct(tempOrder);
                service.editAnOrder(tempOrder);
            }else{
                view.displayString("Your order will be discarded;");
            }
            
        }catch(FlooringMasteryOrderNotFoundException | FlooringMasteryContainsInvalidInputException e){
            view.printErrorMessage(e.getMessage());
        }
       
    }

    private void removeAnOrder() throws FlooringMasteryPersistenceException, FlooringMasteryOrderNotFoundException{
        String tempDate = view.getOrderDate();
        int tempOrderNumber = view.getOrderNumber();
        
        try{
            int keepOrNot = view.displayOrderAndGetCommitment(service.validateOrderByDateAndOrderNumber(tempOrderNumber,tempDate));
            if (keepOrNot == 1 ){
                service.removeAnOrder(tempOrderNumber);
            } else {
                view.displayString("The current order will not be deleted.");
            }
             
        }catch(FlooringMasteryOrderNotFoundException e){
            view.printErrorMessage(e.getMessage());
        }
        
    }

    private void save() throws FlooringMasteryPersistenceException {
        
        try{
            service.save();
            view.displayString("You're data has been persisted to the file!");
        }catch(FlooringMasteryPersistenceException e){
            view.printErrorMessage(e.getMessage());
        }
        
    }

    private Order getOrder() throws FlooringMasteryPersistenceException {
        int a = service.getAllOrders().size() + 1;
        return view.getOrder(a);
    }
    
    private Boolean endProgram() throws FlooringMasteryPersistenceException {
        // which is correct here? Ask Randall!
        int temp  = view.endProgram("Are you sure you wish to exit? \nEnter 1 to exit, 2 to continue."); 
        Boolean toEndOrNot = false;
        if(temp == 1){
            service.save();
            view.sayGoodBye();
        }else{
            toEndOrNot = true;
        }
        return toEndOrNot;    
    }
    
    
//    private Boolean runAdminControls() throws FlooringMasteryPersistenceException {
//        Boolean overallProgram = true;
//        int temp = view.getPassword();
//        
//            if(temp<=5){
//                int userChoice = view.displayAdminMenu();
//
//                boolean keepGoing = true;
//                do{
//                switch(userChoice) {
//                    case 1:
//                        changeMode();
//                        break;
//                    case 2:
//                        changePassword();
//                        break;
//                    case 3:
//                        keepGoing = false;
//                        view.sayGoodBye();
//                        break;
//                    case 4:
//                        keepGoing = false;
//                        overallProgram = false;
//                        view.sayGoodBye();
//                        break;
//                    default:
//                        System.out.println("An error has occured!");
//                }
//            }while(keepGoing);
//        }
//        
//        return overallProgram;
//    }

//    private void changeMode() {
//        // would have to pass that same instance of Config through the admin control methods
//        
//    }

//    private void changePassword() {
//        // would have to pass that same instance of Config through the admin control methods
//    }
//
    private void displayOrderByInput() throws FlooringMasteryPersistenceException {
        int temp = view.getSortingCriteria();
        String x = view.getSortingVariable(temp);
        try{
            view.displayOrderList(service.filterByChoice(temp,x));
        }catch(NumberFormatException e){
            view.displayString("\nYour inputs did not match");
        }
    }

}

