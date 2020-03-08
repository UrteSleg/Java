/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab2.Slegaityte;

import edu.ktu.ds.lab2.gui.ValidationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author uslegaityte
 */
public class PhoneGenerator {
    
    private int startIndex = 0, lastIndex = 0;
    private boolean isStart = true;

    private Phone[] phones;

    public Phone[] generateShuffle(int setSize,
                                 double shuffleCoef) throws ValidationException {

        return generateShuffle(setSize, setSize, shuffleCoef);
    }

    /**
     * @param setSize
     * @param setTake
     * @param shuffleCoef
     * @return Gražinamas aibesImtis ilgio masyvas
     * @throws ValidationException
     */
    public Phone[] generateShuffle(int setSize,
                                 int setTake,
                                 double shuffleCoef) throws ValidationException {

        Phone[] phones = IntStream.range(0, setSize)
                .mapToObj(i -> new Phone.Builder().buildRandom())
                .toArray(Phone[]::new);
        return shuffle(phones, setTake, shuffleCoef);
    }

    public Phone takePhone() throws ValidationException {
        if (lastIndex < startIndex) {
            throw new ValidationException(String.valueOf(lastIndex - startIndex), 4);
        }
        // Vieną kartą Automobilis imamas iš masyvo pradžios, kitą kartą - iš galo.
        isStart = !isStart;
        return phones[isStart ? startIndex++ : lastIndex--];
    }

    private Phone[] shuffle(Phone[] phones, int amountToReturn, double shuffleCoef) throws ValidationException {
        if (phones == null) {
            throw new IllegalArgumentException("Telefonų nėra (null)");
        }
        if (amountToReturn <= 0) {
            throw new ValidationException(String.valueOf(amountToReturn), 1);
        }
        if (phones.length < amountToReturn) {
            throw new ValidationException(phones.length + " >= " + amountToReturn, 2);
        }
        if ((shuffleCoef < 0) || (shuffleCoef > 1)) {
            throw new ValidationException(String.valueOf(shuffleCoef), 3);
        }

        int amountToLeave = phones.length - amountToReturn;
        int startIndex = (int) (amountToLeave * shuffleCoef / 2);

        Phone[] takeToReturn = Arrays.copyOfRange(phones, startIndex, startIndex + amountToReturn);
        Phone[] takeToLeave = Stream
                .concat(Arrays.stream(Arrays.copyOfRange(phones, 0, startIndex)),
                        Arrays.stream(Arrays.copyOfRange(phones, startIndex + amountToReturn, phones.length)))
                .toArray(Phone[]::new);

        Collections.shuffle(Arrays.asList(takeToReturn)
                .subList(0, (int) (takeToReturn.length * shuffleCoef)));
        Collections.shuffle(Arrays.asList(takeToLeave)
                .subList(0, (int) (takeToLeave.length * shuffleCoef)));

        this.startIndex = 0;
        this.lastIndex = takeToLeave.length - 1;
        this.phones = takeToLeave;
        return takeToReturn;
        }
    
}
