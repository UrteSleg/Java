/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab2.Slegaityte;

import edu.ktu.ds.lab2.utils.Ks;
import edu.ktu.ds.lab2.utils.Parsable;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;
/**
 *
 * @author uslegaityte
 */
public class Phone implements Parsable<Phone>{
    
    private String model;
    private String brand;
    private int releaseYear;
    private int size;
    
    public Phone(String data){
        parse(data);
    }
    
    public Phone(String model, String brand, int releaseYear, int size){
        this.model = model;
        this.brand = brand;
        this.releaseYear = releaseYear;
        this.size = size;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getBrand()
    {
        return brand;
    }
    
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    
    public int getReleaseYear()
    {
        return releaseYear;
    }
    
    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void setSize()
    {
        this.size = size;
    }
    
    public String print(){
        return String.format("Model: %-20s Brand: %-20s Release year: %6d Size: %6d", model,brand,releaseYear,size);
    }
    
    public final static Comparator byReleaseYear = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if(((Phone)o1).releaseYear < ((Phone)o2).releaseYear)
                    return -1;
                else if(((Phone)o1).releaseYear > ((Phone)o2).releaseYear)
                    return +1;
                else
                    return 0;
            }
    };
    
    public final static Comparator bySize = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
              
            if(((Phone)o1).size < ((Phone)o2).size)
                return -1;
            else if(((Phone)o1).size > ((Phone)o2).size)
                return +1;
            else
                return 0;
            }
    };
    
    public final static Comparator byBrand = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
            
                return ((Phone)o1).brand.compareTo(((Phone)o2).brand);
            }
    
    
    };
    
    public final static Comparator byModel = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
            
            return ((Phone)o1).model.compareTo(((Phone)o2).model);
            }
            
            };
   
    
    @Override
    public void parse(String data) {
     try {
         
            String[] line = data.split(";", -2);
         
            model = line[0];
            brand = line[1];
            releaseYear = Integer.parseInt(line[2]);
            size = Integer.parseInt(line[3]);         
            
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas -> " + data);
        } catch (IndexOutOfBoundsException e){
            Ks.ern("Trūksta duomenų -> " + data);
        } }

    @Override
    public int compareTo(Phone other) {
        
        if(model.compareTo(other.model) != 0){
            return model.compareTo(other.model);
        }
        
        return brand.compareTo(other.brand);
    }
    
    @Override
    public String toString() {
        return String.format("%s;%s;%d;%d",
                model, brand, releaseYear, size);
    }

    public static class Builder{
        
        private final static Random rand = new Random(System.nanoTime());
        private final static String[] brands = {
            "Nokia", 
            "Apple",
            "Huawei",
            "Samsung",
            "Sony",
            "Ericsson",
            "Blueberry",
            "HTC",
            "Motorola",
            "LG"
        };
        private final static String[] models ={
            "iPhone 3GS",
            "N900",
            "Legend",
            "Wave",
            "Bold 9780",
            "Galaxy Europa",
            "Torch",
            "Wildfire",
            "HD7",
            "Milestone 2",
            "Optimus 7"
        };
      
        public Phone buildRandom(){
            return new Phone(models[rand.nextInt(11)],
                    brands[rand.nextInt(10)],
                    1990 + rand.nextInt(30),
                    130 + rand.nextInt(200));
        }  
    }
    
}
