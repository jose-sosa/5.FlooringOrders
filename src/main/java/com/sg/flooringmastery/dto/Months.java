/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

/**
 *
 * @author josesosa
 */
public enum Months {
    JANUARY ( 1,31),
    FEBRUARY (2,28),
    MARCH (3,31),
    APRIL (4,30),
    MAY (5,31),
    JUNE (6,30),
    JULY (7,31),
    AUGUST (8,31),
    SEPTEMBER (9,30),
    OCTOBER (10,31),
    NOVEMBER (11,30),
    DECEMBER (12,31);
    
    public final int monthInNumber, maxNumberOfDays;
    
    Months(int monthInNumber, int maxNumberOfDays){
       this.monthInNumber = monthInNumber;
       this.maxNumberOfDays = maxNumberOfDays;
    }

    public int getMonthInNumber() {
        return monthInNumber;
    }

    public int getMaxNumberOfDays() {
        return maxNumberOfDays;
    }
    
    
}
