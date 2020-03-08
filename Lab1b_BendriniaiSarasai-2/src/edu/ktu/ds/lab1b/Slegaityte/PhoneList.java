/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.Slegaityte;

import edu.ktu.ds.lab1b.util.ParsableList;
import java.util.Random;

/**
 *
 * @author uslegaityte
 */
public class PhoneList extends ParsableList<Phone>{
    
    public PhoneList(){}
    
    public PhoneList(int count){
        
        String[][] phoneGen = new String[][]{
            {"8 plus","galaxy","H17D12A","X","galaxy s9"}, //model names
            
            {"Apple","Samsung","Huawei","Nokia","Sony Ericson"} //brand names
        };
        
        Random rand = new Random();      
        rand.setSeed(System.nanoTime());
        
        for (int i = 0; i < count; i++) {
            int ModelIndex = rand.nextInt(5);
            int BrandIndex = rand.nextInt(5);
            
            add(new Phone(phoneGen[0][ModelIndex], phoneGen[1][BrandIndex], 2009 + rand.nextInt(40), 10 + rand.nextInt(300)));   
        }
    }
    
    @Override
    protected Phone createElement(String data) {
        return new Phone(data);
    }
}
