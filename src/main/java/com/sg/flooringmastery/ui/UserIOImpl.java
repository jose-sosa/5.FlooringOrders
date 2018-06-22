/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author josesosa
 */
public class UserIOImpl implements UserIO {
    
    
    Scanner myScanner = new Scanner(System.in);
    
    @Override
    public void print(String msg) {
        System.out.println(msg);   
    }    
    
    @Override
    public double readDouble(String prompt) throws NumberFormatException{
        
        Double a = null;
        
        boolean loop = true;
        
        do{
            try{
                System.out.println(prompt);
                a = Double.parseDouble(myScanner.nextLine());
                loop = false;
            }catch(NumberFormatException e){
                
            }
        }while(loop);
        
        return a;
    }
    
    @Override
    public double readDouble(String prompt, double min, double max) throws NumberFormatException{
        Double a = null;
        boolean loop = true;

        do{
            try{
                do{
                    System.out.println(prompt);

                    a = Double.parseDouble(myScanner.nextLine());
                    loop = false;
                    if (a > max || a< min){
                        System.out.println("Your entry was not within the specified bounds."); 
                        loop = true;
                    }

                }while(a > max || a< min);
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);

        return a;
    }

    @Override
    public int readInt(String prompt) throws NumberFormatException{
        int a = 0;
        boolean loop = true;
        
        do{
            try{
                System.out.println(prompt);
                a = Integer.parseInt(myScanner.nextLine());
                loop = false;
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);
        
        return a;
    }

    @Override
    public int readInt(String prompt, int min, int max) throws NumberFormatException{
        int a = 0;
        boolean loop = true;
        
        do{
            try{
                do{
                   System.out.println(prompt);

                   a= Integer.parseInt(myScanner.nextLine());

                   if (a > max || a< min){
                       System.out.println("Your entry was not within the specified bounds."); 
                   }

                }while(a > max || a< min);
                loop = false;
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);
       

         return a;
    }
   
    @Override
    public String readString(String prompt){
        System.out.println(prompt);
        
        String a = myScanner.nextLine();
        return a;
    }
    
    @Override
    public String readString(String prompt, String compare, String toThis){
        System.out.println(prompt);
        String a = myScanner.nextLine();
        
        if(a.equals(toThis)){
            a = compare;
        }
        
        return a;
    }

    @Override
    public double readDouble(String prompt, double min, Double compare, String toThis) throws NumberFormatException {
        Double a = null;
        boolean loop = true;

        do{
            try{
                do{
                    System.out.println(prompt);
                    String b = myScanner.nextLine();
                    
                    if(b.equals(toThis)){
                        a = compare;
                    }else{
                        a = Double.parseDouble(b);
                    }
                    loop = false;
                    if (a< min){
                        System.out.println("Your entry was not within the specified bounds."); 
                        loop = true;
                    }

                }while(a< min);
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);

        return a;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) throws NumberFormatException {
        BigDecimal a = null;
        boolean loop = true;
        
        do{
            try{
                System.out.println(prompt);
                a = new BigDecimal(myScanner.nextLine());
                loop = false;
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
        }while(loop);
        
        return a;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal oldValue) throws NumberFormatException{
        BigDecimal temp = null;
        boolean loop =true;
        System.out.println(prompt);
        String a = myScanner.nextLine();
        
        do{
            try{
                if(a.equals("")){
                    temp = oldValue;
                }else{
                    temp = new BigDecimal (a);
                }
                
            }catch(NumberFormatException e){
                print("Incorrect data type");
            }
            loop = false;
        }while(loop);
        
        return temp;
    }

   
}
