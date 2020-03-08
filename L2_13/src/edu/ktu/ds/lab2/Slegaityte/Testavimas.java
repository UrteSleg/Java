/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab2.Slegaityte;

import edu.ktu.ds.lab2.utils.BstSet;
import edu.ktu.ds.lab2.utils.Ks;
import edu.ktu.ds.lab2.utils.SortedSet;

/**
 *
 * @author uslegaityte
 */
public class Testavimas {
    
    public static void main(String[] args) {
        
        BstSet<Phone> aa = new BstSet(Phone.bySize);
        BstSet<Phone> aa2 = new BstSet(Phone.bySize);
        for (int i = 0; i < 10; i++) {
            aa.add(new Phone.Builder().buildRandom());
        }
        for (int i = 0; i < 5; i++) {
            aa2.add(new Phone.Builder().buildRandom());
        }
        
        Phone b = new Phone.Builder().buildRandom();
        Phone b2 = new Phone.Builder().buildRandom();
        aa.add(b);
        aa.add(b2);
        Ks.oun(aa.toVisualizedString(""));
        //Ks.oun(aa2.toVisualizedString(""));
        
        for (Phone phone : aa) {
            Ks.oun(phone.print());
        }
        Ks.oun("");
//        for (Phone phone : aa2) {
//            Ks.oun(phone.print());
//        }
        Ks.oun("Higher: ");
        higherTest(aa);
        
        Ks.oun("PollLast: ");
        pollLastTest(aa);
        
        Ks.oun("addAllTest: ");
        addAllTest(aa2,aa);
        
        Ks.oun("retainAllTest: ");
        retainAllTest(aa,aa2);
        
        Ks.oun("Subset/headset Test: ");
        SubsetTest(aa, b, b2);
        
       
    }

    private static void addAllTest(BstSet<Phone> aa2, BstSet<Phone> aa) {

        Ks.oun("");
        aa2.addAll(aa);
        Ks.oun(aa2.toVisualizedString(""));
        for (Phone phone : aa2) {
            Ks.oun(phone.print());
        }
        Ks.oun("");
    }

    private static void higherTest(BstSet<Phone> aa) {
        Ks.oun("");
        Phone bb = new Phone.Builder().buildRandom();
        Ks.oun(bb.print());
        Ks.oun(aa.higher(bb).print());
        Ks.oun("");
    }

    private static void pollLastTest(BstSet<Phone> aa) {
        Ks.oun("");
        Ks.oun(aa.pollLast());
        Ks.oun("");
        for (Phone phone : aa) {
            Ks.oun(phone.print());
        }
        Ks.oun("");
    }
    
    private static void retainAllTest(BstSet<Phone> aa, BstSet<Phone> aa2) {
        Ks.oun("");
        Ks.oun(aa.retainAll(aa2));
        Ks.oun(aa2.toVisualizedString(""));
        for (Phone phone : aa2) {
            Ks.oun(phone.print());
        }
        Ks.oun("");
    }

    private static void SubsetTest(BstSet<Phone> aa, Phone b, Phone b2) {
        Ks.oun("");
        SortedSet<Phone> ss = aa.headSet(b2, true);
        //Set<Book> ss = aa.tailSet(b);
        //SortedSet<Phone> ss = aa.subSet(b, false, b2, true);
        Ks.oun(b.print());
        Ks.oun(b2.print());
        Ks.oun("");
        
        for (Phone phone : ss) {
            Ks.oun(phone.print());
        }
    }
}
