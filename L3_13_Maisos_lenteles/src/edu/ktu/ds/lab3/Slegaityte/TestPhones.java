/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Slegaityte;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.ParsableHashMap;
import edu.ktu.ds.lab3.utils.ParsableMap;

import java.util.Locale;
/**
 *
 * @author uslegaityte
 */
public class TestPhones {
    
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        executeTest();
    }

    public static void executeTest() {
        Phone c1 = new Phone.Builder().buildRandom();
        Phone c2 = new Phone.Builder().buildRandom();
        Phone c3 = new Phone.Builder().buildRandom();
        Phone c4 = new Phone.Builder().buildRandom();
        Phone c5 = new Phone.Builder().buildRandom();
        Phone c6 = new Phone.Builder().buildRandom();
        Phone c7 = new Phone.Builder().buildRandom();

        // Raktų masyvas
        String[] phonesID = {"PhoneCode100", "PhoneCode202", "PhoneCode330", "PhoneCode004", "PhoneCode050", "PhoneCode600", "PhoneCode707", "PhoneCode888"};
        int id = 0;
        ParsableHashMap<String, Phone> PhoneMap
                = new ParsableHashMap<>(String::new, Phone::new, HashType.DIVISION);

        // Reikšmių masyvas
        Phone[] phones = {c1, c2, c3, c4, c5, c6, c7};
        for (Phone c : phones) {
            PhoneMap.put(phonesID[id++], c);
        }
        PhoneMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(PhoneMap.contains(phonesID[6]));
        Ks.oun(PhoneMap.contains(phonesID[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(PhoneMap.remove(phonesID[1]));
        Ks.oun(PhoneMap.remove(phonesID[7]));
        PhoneMap.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(PhoneMap.get(phonesID[2]));
        Ks.oun(PhoneMap.get(phonesID[7]));
        Ks.oun("Vidutinis grandinėlės ilgis:");
        Ks.oun(PhoneMap.averageChainSize());
        Ks.oun("Pakeista vertė:");
        Ks.oun(PhoneMap.replace("PhoneCode999", c7));
        PhoneMap.println("Porų išsidėstymas atvaizdyje pagal raktus PO PAKEITIMO");
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(PhoneMap);
    }
}
