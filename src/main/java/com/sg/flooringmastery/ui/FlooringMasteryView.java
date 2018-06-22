/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Months;
import com.sg.flooringmastery.dto.Order;
import java.util.List;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryView {
    UserIO io;
    Months month1;
    
    
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public void sayHello() {
        
        io.print("\nWelcome to Sosa Incorporated. Where customer satisfaction is our number one goal!\n");
    }

    public void displayOrderList(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            io.print(currentOrder.toString());
        }
        io.readString("Please hit enter to continue.");
     }
    
    public int getMenuSelection() {
        
        io.print("\n How would you like to proceed?");
        return io.readInt("\n *        *        * \n1. Display Orders \n2. Add an Order \n3. Edit an Order \n4. Remove an Order \n5. Save Current Work \n6. Quit \n *        *        *", 0, 6);
    }

    public void sayGoodBye() {
        io.print("\n Have a nice day! \n");
    }

//    public int displayAdminMenu() {
//        io.print("\n How would you like to proceed?");
//        return io.readInt("\n *        *        * \n1. Change the current mode \n2. Change the password \n3. Exit admin controls \n4. Exit entire program",1,4);
//       
//    }

    public void displayString(String message) {
        io.print(message);
    }

    public int getOrderNumber() {
        return io.readInt("Which order number are you interested in?");
    }

    public Order getOrder(int nextOrderNumber) {

        String orderDate = getOrderDate();
        
        Order tempOrder = new Order(nextOrderNumber, orderDate);
        
        tempOrder.setCustomerName(io.readString("What is the customer's name?"));
        tempOrder.setStateAbbreviation(io.readString("What is the State Abbreviation?"));
        tempOrder.setArea(io.readBigDecimal("What is the area?"));
        tempOrder.setProductType(io.readString("What is the product type?"));
        
        return tempOrder;//
    }
    
    public String getOrderDate(){
        
        
        int temp = io.readInt("\n Which year are you interested in? \n",0,3141);
        
        for(Months month: Months.values()){
            io.print(month + " (" + month.getMonthInNumber() +  ") : 1 to " + month.getMaxNumberOfDays());
        }
        
        int temp1 = io.readInt("\n Please enter the month that you are interested in (by month number)? \n",1,12);
        month1 = Months.values()[temp1 - 1];
        
        int temp2 = io.readInt("\n Please enter the day of the month. \n",1,month1.getMaxNumberOfDays());
        
        return temp + "-" + temp1 + "-" + temp2;
    }

    public void printErrorMessage(String message) {
        io.print("\n=== ERROR ===\n" + message + "\n=== ERROR ===\n");
        
    }

    public int displayOrderAndGetCommitment(Order temp) {
        
        int whatToDo = io.readInt(temp.toString() + "\n \n If you would like to proceed with these changes press 1, otherwise enter 2 to abandon changes",1,2);
        
        return whatToDo;
    }

    public int endProgram(String message) {
        return io.readInt(message,1,2);
    }

    public Order getOrderForEditing(Order tempOrder) { // kinda like placeHolder, jajaja
        tempOrder.setCustomerName(io.readString("What is the customer's name (" + tempOrder.getCustomerName() + ")?"  ,tempOrder.getCustomerName(),""));
        tempOrder.setStateAbbreviation(io.readString("What is the State Abbreviation (" + tempOrder.getStateAbbreviation() + ")?"  ,tempOrder.getStateAbbreviation(),""));
        
        tempOrder.setArea(io.readBigDecimal("What is the area ("  + tempOrder.getArea() + ")?",tempOrder.getArea()));
        
        tempOrder.setProductType(io.readString("What is the product type ("  + tempOrder.getProductType() + ")?"  ,tempOrder.getProductType(),""));
        tempOrder.calculateOtherValues();
        
        return tempOrder;
    }
    
//    public int getPassword() {
//        String passwordChecker;
//        int count=1;
//        do{
//             passwordChecker = io.readString("If you want access to admin capabilities please enter the administrative password");
//             
//             count++;
//             
//        }while(count<4 && !passwordChecker.equalsIgnoreCase("password"));
//        
//        return count;
//    }
    
    public int getSortingCriteria(){
        io.print("Your choices for sorting are : \n 1. State \n 2. Area \n 3. Order Number \n 4.Customer Name \n 5. State Abbreviation  \n 6. Product Type"
        + "\n 7. Tax Rate \n 8. Cost Per Square Feet \n 9. Labor Cost Per Square Feet \n 10. Material Cost \n 11. Labor Cost \n 12. Tax Charged \n 13. Total \n 14. Date");
        int b = io.readInt("By which category would you like to sort by?",1,14);
        
        return b;
    }   
       
    public String getSortingVariable(int b){
        
        String alpha = io.readString("Please input the sorting parameter (i.e. a name, a date (YYYY-M-D), a price or an order number)?");
        
        return alpha;
    }

}

