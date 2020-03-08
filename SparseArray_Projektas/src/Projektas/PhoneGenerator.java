/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author uslegaityte
 */
public class PhoneGenerator {
    private static Phone [] phones;
    private static int pradinisIndeksas = 0, galinisIndeksas = 0;
    private static boolean arPradzia = true;
    
    private static Integer [] numbers;
    public static Phone [] generuoti(int kiekis){
        phones = new Phone[kiekis];
        for (int i = 0; i < kiekis; i++) {
            phones[i] = new Phone.Builder().buildRandom();
        }
        return phones;
    }
    public static Integer [] generuotiIrIsmaisytiSk(int kiekis){
        numbers = new Integer[kiekis];
        Random random = new Random();
        for (int i = 0; i < kiekis; i++) {
           numbers[i] = random.nextInt(kiekis);
           
        }
        Collections.shuffle(Arrays.asList(numbers));
        return numbers;
    }
    
    public static Phone [] generuotiIrIsmaisyti (int aibesDydis, 
            double isbarstymoKoeficientas){
        return generuotiIrIsmaisyti(aibesDydis, isbarstymoKoeficientas);
    }
    
    /**
     * @param aibesDydis
     * @param aibesImtis
     * @param isbarstymoKoeficientas
     * @return GraziPhone aibesImtis ilgio masyvas
     * @throws MyException
     */
    public static Phone[] generuotiIrIsmaisyti(int aibesDydis,
            int aibesImtis, double isbarstymoKoeficientas){
        phones = generuoti(aibesDydis);
        return  ismaisyti(phones, aibesImtis, isbarstymoKoeficientas);
    }
    public static Phone [] ismaisyti(Phone [] phoneBase,
            int kiekis, double isbarstKoef){
        if (phoneBase == null){
            throw  new IllegalArgumentException("phoneBase yra null");
        }
        int likusiuKiekis = phoneBase.length - kiekis;
        int pradziosIndeksas = (int)(likusiuKiekis * isbarstKoef/ 2);
        
        Phone [] FirstGroup = Arrays.copyOfRange(phoneBase,
                pradziosIndeksas, pradziosIndeksas+kiekis);
        Phone [] LastGroup = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(phoneBase, 0, pradziosIndeksas)),
                     Arrays.stream(Arrays.copyOfRange(phoneBase, pradinisIndeksas + kiekis, phoneBase.length)))
                .toArray(Phone [] :: new );
        
        Collections.shuffle(Arrays.asList(FirstGroup)
                .subList(0, (int)(FirstGroup.length * isbarstKoef)));
           Collections.shuffle(Arrays.asList(LastGroup)
                   .subList(0, (int) (LastGroup.length * isbarstKoef)));
           
           PhoneGenerator.pradinisIndeksas = 0;
           galinisIndeksas = LastGroup.length - 1;
           PhoneGenerator.phones = LastGroup;
        return FirstGroup;
        
        
        
    }
    public static Phone gautiIsBazes(){
        //Vieną kartą tel imamas iš masyvo pradžios, kitą kartą iš galo
        arPradzia = !arPradzia;
        return phones[arPradzia ? pradinisIndeksas++ : galinisIndeksas--];
    }
}
