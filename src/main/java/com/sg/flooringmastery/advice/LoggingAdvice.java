/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.advice;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.FlooringMasteryAuditDaoImpl;
import com.sg.flooringmastery.dao.exceptions.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryNoOrdersForTheRequestedDateException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author josesosa
 */
public class LoggingAdvice {
    FlooringMasteryAuditDao auditDao;
    int count = 0;
    int invalidCount = 0;
    int orderNotFoundCount = 0;
    int dateCount = 0;
 
    public LoggingAdvice(FlooringMasteryAuditDaoImpl auditDao) {
        this.auditDao = auditDao;
    }
    
   
    
    public void createAuditEntry(JoinPoint jp, Throwable ex) throws FlooringMasteryContainsInvalidInputException, FlooringMasteryNoOrdersForTheRequestedDateException, FlooringMasteryOrderNotFoundException {
        if (ex instanceof FlooringMasteryContainsInvalidInputException ){
            count = invalidCount++;
        }else if (ex instanceof FlooringMasteryOrderNotFoundException){
            count = orderNotFoundCount++;
        }else{
            count = dateCount++;
        }
        
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " : " + ex.toString() + " (" + count + ") ";
      
        for (Object currentArg : args) {
            auditEntry += ": " + currentArg;
            
        }
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringMasteryPersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}
