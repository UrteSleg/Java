/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

import java.util.Objects;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author uslegaityte
 */
public class Phone implements Comparable<Phone>{

    private final String ID;
    private String brand;
    private String model;
    private int size;
    private String seller;
    private int releaseyear;
    private Boolean smartphone;
    
    private static final String idCode = "ID";   //  ***** nauja
    private static int idNr = 10;
    final static private int acSIZE1 = 16;
    final static private int acSIZE2 = 512;

    public Phone(String brand, String model, int size, String seller, int releaseyear, Boolean smartphone) {
        
        this.ID = idCode + (idNr++);
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.seller = seller;
        this.releaseyear = releaseyear;
        this.smartphone = smartphone;
    }

    public Phone() {
        ID = idCode + (idNr++);
    }
    public Phone(Builder builder){
        this.ID = idCode + (idNr++);
        this.brand = builder.bra;
        this.model = builder.mod;
        this.size = builder.siz;
        this.seller = builder.sel;
        this.releaseyear = builder.rel;
        this.smartphone = builder.sma;
    }

    public void setSmartphone(Boolean smartphone) {
        this.smartphone = smartphone;
    }

    public String getID() {
        return ID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getSize() {
        return size;
    }

    public String getSeller() {
        return seller;
    }

    public int getReleaseYear() {
        return releaseyear;
    }

    public Boolean getSmartphone() {
        return smartphone;
    }
    public Phone(String dataString) {
        ID = idCode + (idNr++);    
        this.parse(dataString);
    }
    
    public Phone create(String dataString) {
        Phone b = new Phone();
        b.parse(dataString);
        return b; 
    }

    public String validate() {
        String klaidosTipas = "";
        if (size < acSIZE1 || size > acSIZE2) {
            klaidosTipas = "Netinkamas dydis, turi būti ["
                    + acSIZE1 + ";" + acSIZE2 + ")";
        }
        return klaidosTipas;
    }

    private void parse(String dataString) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(dataString);
            this.brand = ed.next();
            this.model = ed.next();
            this.size = ed.nextInt();
            this.seller = ed.next();
            this.releaseyear = ed.nextInt();
            this.smartphone = ed.nextBoolean();
        } catch (InputMismatchException e) {
            System.out.println("Blogas duomenų formatas apie telefoną -> " + dataString);
        } catch (NoSuchElementException e) {
            System.out.println("Trūksta duomenų apie telefoną -> " + dataString);
        }
        
    }

    @Override
    public int compareTo(Phone e) {
        return getID().compareTo(e.getID());
    }

    public final static Comparator<Phone> bySeller
            = new Comparator<Phone>() {
        @Override
        public int compare(Phone b1, Phone b2) {
            return b1.getSeller().compareTo(b2.getSeller());
        }
    };

    @Override
    public String toString() {
        return getID() + "="+brand+"_"+model+" "+ size +" "+seller+" "+releaseyear+" " + smartphone;       
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Phone other = (Phone) obj;
        if (this.ID.equals(other.ID)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    public static class Builder {

        private final static Random RANDOM = new Random(1949);  // Atsitiktinių generatorius
        private final static String[][] BRANDS = { // GALIMU PAVARDZIU IR VARDU MASYVAS
            {"Nokia", "Apple", "Huawei", "Samsung", "Sony"},
            {"Ericsson", "Blueberry", "HTC", "Motorola", "LG", "NewPhone"},
            {"Brand1", "Brand2", "Brand3"},
            {"1Brand1", "2Brand2", "3Brand3", "4Brand4"},
            {"Super", "Good", "Chinese"},
            {"Belekas", "Any", "Perfect", "Name"}
        };
        private final static String[] SELLER ={ "omnitel", "bite",
            "tele2", "maxima", "topocentras", "avitela"};
        private final static String[] SMARTPHONE = { "true", "false"};
        

        private String mod = "";
        private String bra = "";
        private int siz = -1;
        private String sel = "";
        private int rel = -1;
        private boolean sma = false;

        public Phone build() {
        return new Phone(this);
        }

        public Phone buildRandom() {
            int ma = RANDOM.nextInt(BRANDS.length);        
            int mo = RANDOM.nextInt(BRANDS[ma].length - 1) + 1;            
            int size = 16 + RANDOM.nextInt(45);
            return new Phone(
                    BRANDS[ma][mo], 
                    BRANDS[ma][0], 
                    size,
                    SELLER[RANDOM.nextInt(SELLER.length)],
                    size-16,
                    RANDOM.nextBoolean());
        }

        public Builder mod(String mod) {
            this.mod = mod;
            return this;
        }

        public Builder bra(String bra) {
            this.bra = bra;
            return this;
        }
        public  Builder sma(boolean sma){
            this.sma = sma;
            return this;
        }
        
        public Builder siz(int siz) {
            this.siz = siz;
            return this;
        }

        public Builder rel(int rel) {
            this.rel = rel;
            return this;
        }

        public Builder sel(String sel) {
            this.sel = sel;
            return this;
        }
    }
    
}