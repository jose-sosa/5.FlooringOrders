/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.service.exceptions.FlooringMasteryContainsInvalidInputException;
import com.sg.flooringmastery.service.exceptions.FlooringMasteryOrderNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryServiceTest {
    private FlooringMasteryService service;
  
    
    public FlooringMasteryServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("serviceLayer", FlooringMasteryServiceImpl.class);
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testValidateOrderForRemovalOrEditing() throws Exception {
        try {
            service.validateOrderByDateAndOrderNumber(3,"2018-3-20");
        fail("Expected FlooringMasteryOrderNotFoundException was not thrown.");
        } catch (FlooringMasteryOrderNotFoundException e) {
            return;
        }
        
    }
    
    @Test
    public void testVerifyTaxAndProduct() throws Exception{
        try {
            service.verifyTaxAndProduct(service.getAnOrder(2));
        fail("Expected FlooringMasteryContainsInvalidInputException was not thrown.");
        } catch (FlooringMasteryContainsInvalidInputException e) {
            return;
        }
        
    }
    
}
