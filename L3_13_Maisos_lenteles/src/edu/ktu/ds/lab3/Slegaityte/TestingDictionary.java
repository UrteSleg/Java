/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Slegaityte;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.Ks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author uslegaityte
 */
public class TestingDictionary {
    
    public static HashMap<String, String> zodynas = new HashMap<>();

    static double loadValues(String filePath) throws FileNotFoundException {
        
        Scanner file = new Scanner(new File(filePath));
        long prad = System.nanoTime();
        while (file.hasNextLine()) {
            String value = file.nextLine();
            zodynas.put(value, value);
        }
        return (System.nanoTime() - prad)/1e9;
    }
    
    static double getValue(String Key){
        long prad = System.nanoTime();
        zodynas.get(Key);     
        return (System.nanoTime() - prad)/1e9;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Uzkrovimo laikas: " + loadValues(Ks.getDataFolder() + "\\zodynas.txt"));
        System.out.println("Zodyno dydis: " + zodynas.size());
        
        System.out.println(zodynas.getMaxChainSize());
        System.out.println(getValue("zebra"));
    }
}
