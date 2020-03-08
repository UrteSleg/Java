/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.Slegaityte;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;

/**
 *
 * @author uslegaityte
 */
public class TestLibrary {
    
     PhoneList phones = new PhoneList();
    
    
    void execute(){
        
       Methods();
    }
    
    void Methods(){
        Phone b1 = new Phone("Model1", "Brand1", 1111, 11);
        Phone b2 = new Phone("Model2", "Brand2", 2222, 22);
        Phone b3 = new Phone("Model3", "Brand3", 3333, 33);
        Phone b4 = new Phone("Model4", "Brand4", 4444, 44);
        Phone b5 = new Phone("Model5", "Brand5", 5555, 55);
        Phone b6 = new Phone("Model6", "Brand6", 6666, 66);
        Phone b7 = new Phone("Model7", "Brand7", 7777, 77);
        
        phones.add(b1);
        phones.add(b2);
        phones.add(b3);
        phones.add(b4);
        phones.add(b5);
        phones.add(b6);
        phones.add(b7);
        
        for(Phone phone : phones)
        {
            Ks.oun(phone);
        }
        
        Ks.oun("");
        LinkedList<Phone> a = new LinkedList<Phone>();
        a.add(b1);
        a.add(b2);
        a.add(b3);
        a.add(b4);
        a.add(b5);
        a.add(b6);
        a.add(b7);
        
        //b0 pakeicia b4
        Ks.oun("Set:");
        a.set(0, b4);
        for (int i = 0; i < a.size(); i++) 
        {
            Ks.oun(a.get(i));
        }
        
        //Pasalina b6
        Ks.oun("Remove:");
        a.remove(5);
        for (int i=0; i<a.size(); i++)
        {
            Ks.oun(a.get(i));
        }
        
        //patikrina ar yra b6
        Ks.oun("Contains:");
        String answer = a.contains(b6) ? "Exists" : "Does not exist";
        Ks.oun(answer);
        
        //Pasalina pirma elementa is saraso
        Ks.oun("RemoveFirst:");
        a.removeFirst();
        for (int i=0; i<a.size(); i++)
        {
            Ks.oun(a.get(i));
        }
        
        //Pasalina intervala
        Ks.oun("RemoveRange:");
        a.removeRange(0, 1);
        for (int i=0; i<a.size(); i++)
        {
            Ks.oun(a.get(i));
        }
        
        //LastIndexOf
        Ks.oun("LastIndexOf:");
        int ats = a.lastIndexOf(b3);
        Ks.oun(ats);
    }
    
    
    public static void main(String[] args) {
        new TestLibrary().execute();
    }    
}
