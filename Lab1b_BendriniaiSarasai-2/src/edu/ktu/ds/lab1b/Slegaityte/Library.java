/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.Slegaityte;

/**
 *
 * @author uslegaityte
 */
public class Library {
    
    public PhoneList phones = new PhoneList();
    
    // Get phones from a specified brand
    public PhoneList getByBrand(String brand){
        PhoneList newList = new PhoneList();
        
        for (Phone phone : phones) {
            if(phone.getBrand().compareTo(brand) == 0){
                newList.add(phone);
            }
        }
        return newList;
    }
    
    // Get phone from a specific year
    public PhoneList getFromYear(int year){
        PhoneList newList = new PhoneList();
        
        for (Phone phone : phones) {
            if(phone.getReleaseYear() == year){
                newList.add(phone);
            }
        }
        return newList;
    }
    
    // Gets phones from a range of years
    public PhoneList getFromYearRange(int startYear, int endYear){
        PhoneList newList = new PhoneList();
        
        for (Phone phone : phones) {
            if(phone.getReleaseYear() >= startYear &&
               phone.getReleaseYear() <= endYear){
                newList.add(phone);
            }
        }
        return newList;
    }
}
