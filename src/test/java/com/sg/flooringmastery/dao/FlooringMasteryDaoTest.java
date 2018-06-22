/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author josesosa
 */
public class FlooringMasteryDaoTest {
    
    
    FlooringMasteryDao dao = new FlooringMasteryDaoStubImpl();
    
    public FlooringMasteryDaoTest() {
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

    @org.junit.Test
    public void testGetAllOrders() throws Exception {
        dao.getAllOrders();
        assertEquals(2, dao.getAllOrders().size());
    }
    
    @org.junit.Test
    public void testRemoveAnOrder() throws Exception {
        Order temp = dao.getAnOrder(2);
        dao.removeAnOrder(temp);
        assertEquals(1, dao.getAllOrders().size()); // keep in mind that my hashmap.size = 1
    }
    
    @org.junit.Test
    public void testAddAnOrder() throws Exception{
        Order temp = new Order(2,"2012-12-12");
        dao.addAnOrder(temp);
        assertEquals(2, dao.getAllOrders().size()); // keep in mind taht my hashmap.size should equal 2
    }
    
}
