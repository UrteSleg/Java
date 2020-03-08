/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

import java.util.ArrayList;
/**
 *
 * @author uslegaityte
 */
public class Tests {
    public Tests() throws InterruptedException{
        testuoti();
    }
    void testuoti() throws InterruptedException{
        Phone b1 = new Phone("Nokia iPhone3Gs 16 omnitel 2006 true");
        Phone b2 = new Phone("Apple N900 216 bite 2011 true");
        Phone b3 = new Phone("Huawei Legend 23 tele2 2001 false");
        Phone b4 = new Phone("Samsung Wave 216 maxima 2011 true");
        Phone b5 = new Phone("Sony Bold9780 25 topocentras 2009 false");
        Phone b6 = new Phone("Ericsson GalaxyEuropa 55 avitela 2006 false");
        Phone b7 = new Phone("Blueberry Torch 49 omnitel 2000 false");
        Phone b8 = new Phone("HTC Wildfire 46 bite 2003 false");
        Phone b9 = new Phone("Motorola HD7 60 tele2 2014 true");
        Phone b10 = new Phone("LG Milestone2 84 maxima 2017 true");
        
        Phone[] sarasasSuT = {null, b9, null,b6,b5,null,b7,b1,b2,b3,null,null,null,null};
        MySparseArray<Phone> sarasasBeT = convertToSparseArray(sarasasSuT);
        
        System.out.println("=====Sarašas pradžioje=====\n"+sarasasBeT.toString());        
        System.out.println("=====Sarašas indexOfValue(b3)=====\n" + sarasasBeT.indexOfValue(b3));
        sarasasBeT.remove(4);
        System.out.println("=====Sarašas po šalinimo 4 elemento=====\n"+sarasasBeT.toString());
        
        System.out.println("=====Sarašas get(1)=====\n" + sarasasBeT.get(0).toString());
        System.out.println("=====Sarašas valueAt(3)=====\n" + sarasasBeT.valueAt(3).toString());
        sarasasBeT.clear();
        System.out.println("=====Sarašas po clear=====\n" + sarasasBeT.toString());
        System.out.println("=====Sarašas isEmpty?=====\n" + sarasasBeT.isEmpty());
        System.out.println("");
        
        System.out.println("=====Greitaveika=====");
        Greitaveika a = new Greitaveika();
        a.testGreitaveika();
    }
    MySparseArray<Phone> convertToSparseArray(Phone[] list){
        MySparseArray<Phone> newSparse = new MySparseArray<>(list.length);
        for(int i = 0; i < list.length; i++){
            newSparse.put(i, list[i]);
        }
        return newSparse;
    }
    
    public static void main(String[] args) throws InterruptedException {
        new Tests();        
    }
}
