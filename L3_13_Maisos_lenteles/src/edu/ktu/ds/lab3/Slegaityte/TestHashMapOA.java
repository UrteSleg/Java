/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Slegaityte;

import edu.ktu.ds.lab3.utils.Ks;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.io.IOException;
/**
 *
 * @author uslegaityte
 */
public class TestHashMapOA {
    static HashMapOA<String, Phone> t = new HashMapOA<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader wr = new BufferedReader(new FileReader(Ks.getDataFolder() + "\\phonesData.txt"));

        for (int i = 0; i < 100; i++) {
            Phone a = new Phone(wr.readLine());
            t.put(a.getModel(), a);
        }
        wr.close();

        System.out.println("Size: " + t.size());
        System.out.println("Rehash: " + t.rehashesCounter);
        
        System.out.println("Value got: " + t.get("Torch").toString());
        System.out.println("Removed value: " + t.remove("Torch").toString());
        System.out.println("Value got after removal: " + t.get("Torch").toString());
        t.put("Torch", new Phone("New", "phone", 2015, 69));
        System.out.println("Value got: " + t.get("Torch").toString());
        
    }
}
