/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryNoOrdersForTheRequestedDateException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author josesosa
 */
public class App {
     public static void main(String[] args) throws FlooringMasteryPersistenceException, FlooringMasteryContainsInvalidInputException, FlooringMasteryOrderNotFoundException, FlooringMasteryNoOrdersForTheRequestedDateException  { //throws FlooringMasteryPersistenceException

        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        
        Config configuration = new Config();
       
        FlooringMasteryController controller = 
           ctx.getBean(configuration.getMode(), FlooringMasteryController.class);
        
        controller.run(configuration.getPassword(), configuration.getMode());
     }
    
}
//------------------------------------------------------------------------------
////        for(Method method : Order.class.getMethods()){
////            if(method.getName().startsWith("get")){
////                System.out.println(method);
////            }
////        }
//
//        List<Method> temp = null;
//        for(Method method : Order.class.getMethods()){
//            if(method.getName().startsWith("get")){
//                temp.add(method);
//            }
//        }
