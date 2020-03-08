/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

import java.util.ArrayList;
import java.util.Locale;
import Projektas.Timekeeper;
/**
 *
 * @author uslegaityte
 */
public class Greitaveika {
    
   

     void testGreitaveika(){
        int x = 0;
        
        System.out.println("ArrayList<integer> <=> MySparseArray<Integer>");
        
        ArrayList<Integer> y1 = new ArrayList<Integer>();
        ArrayList<Integer> temp1 = new ArrayList<Integer>();
        MySparseArray<Integer> y2 = new MySparseArray<Integer>(100000);
        MySparseArray<Integer> temp2 = new MySparseArray<Integer>(100000);
        for (int i = 0; i < 100000; i++) {
            y1.add(x);
            y2.put(x, x);
            x++;
        }
        x=0;
        long t0 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            y1.get(x);
            x++;
        }
        x=0;
         long t1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            y2.get(x);
            x++;
        }
        long t2 = System.nanoTime();
        System.out.printf("%7s <=> %7s \n", t2 - t1, t1 - t0);
        if((t1-t0)>(t2-t0)) System.out.println("ArrayList<integer> veikia greičiau");
        else System.out.println("MySparseArray<Integer> veikia greičiau");
    }

    public static void main(String[] args) throws InterruptedException {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new Greitaveika().testGreitaveika();
    }
}

