/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.Slegaityte;

import edu.ktu.ds.lab1b.util.Ks;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author uslegaityte
 */
public class Greitaveika {
    
    void testGreitaveika1(){
        
        double sum = 0;
        
        Ks.oun("kiekis(*k) x*x <=> Math.pow(x,2);");
        int a=0, b=0;
        for (int i = 1; i <= 5; i++) {
            long t0 = System.nanoTime();
            for(int j = 10000; j < 100000*i; j+= 10000){
                sum = j*j;  
            }
            long t1 = System.nanoTime();
            
            long t2 = System.nanoTime();
            for(int j = 10000; j < 100000*i; j+= 10000){
                 sum = Math.pow(j, 2);  
            }
            long t3 = System.nanoTime();
            
            Ks.oun(100000*i + "(" + i + ")  " + (t1 - t0) +  " <=> " + (t3 - t2));
            
            if((t1-t0)>(t3-t2)) 
            {
                Ks.oun("*k greitesnė");
                a++;
            }
            else 
            {
                Ks.oun("Math greičiau");
                b++;
            }
        }
        if(a>b) Ks.oun("Paprasta daugyba veikia greičiausiai");
        else Ks.oun("Funkcija Math.pow veikia greičiausiai");
    }
    
    void testGreitaveika15(){
        int x = 0;
        
        Ks.oun("ArrayList<integer> <=> LinkedList<Integer>");
        
        ArrayList<Integer> y1 = new ArrayList<Integer>();
        ArrayList<Integer> temp1 = new ArrayList<Integer>();
        LinkedList<Integer> y2 = new LinkedList<Integer>();
        LinkedList<Integer> temp2 = new LinkedList<Integer>();
        long t0 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            y1.add(x);
            y2.add(x);
            x++;
            if (i%7 == 0)
            {
                temp1.add(x);
                temp2.add(x);
            }
        }
        boolean a1 = y1.containsAll(temp1);
        long t1 = System.nanoTime();
        boolean a2 = y2.containsAll(temp2);
        long t2 = System.nanoTime();
        Ks.ouf("%7s <=> %7s \n", t1 - t0, t2 - t0);
        if((t1-t0)>(t2-t0)) Ks.oun("ArrayList<integer> veikia greičiau");
        else Ks.oun("LinkedList<Integer> veikia greičiau");
    }
    
    
    public static void main(String[] args) {
        new Greitaveika().testGreitaveika1();
        new Greitaveika().testGreitaveika15();
    }
}
