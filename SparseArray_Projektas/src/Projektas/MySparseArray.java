/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projektas;

/**
 *
 * @author uslegaityte
 */
public class MySparseArray<E> implements SparseArrayClassic<E> {
    
    private int[] mKeys;
    private Object[] mValues;
    private int mSize;
    
    public MySparseArray() {
        this(10);
    }
    
    public MySparseArray(int capacity) {
        mKeys = new int[capacity];
        mValues = new Object[capacity];
        mSize = 0;
    }
    
    //pridejimas elemento į masyvą
    //@Override
    public void put(int key, E value) {
        if (value == null) {
            return;
        }
        // ieško ar su tokiu key jau yra liste
        int i = searchKeyInKeys(key);
        if (i >= 0) {
            mValues[i] = value;
        } else if (mSize == 0) {
            mKeys[0] = key;
            mValues[0] = value;
        }// jei nėra - insertina pagal tvarka
        else {
            insert(key, value);
        }
        mSize++;
    }
    
    //Įterpia elementą į reikiamą vietą
    private void insert(int key, E value) {
        int i = 0;
        //while(mKeys[i] < key && (mKeys[i+1] > key || mKeys[i+]))
        while (mSize > i && mKeys[i] < key) {
            i++;
        }
        // jei iterpt gale
        if (i == mSize - 1 && mKeys[i] < key) {
            mKeys[i + 1] = key;
            mValues[i + 1] = value;
        } //iterpt prieš i
        else {
            int[] subKeys = mKeys.clone();
            Object[] subValues = mValues.clone();
            mKeys[i] = key;
            mValues[i] = value;
            i++;
            while (i < mSize + 1) {
                mKeys[i] = subKeys[i - 1];
                mValues[i] = subValues[i - 1];
                i++;
            }
        }
    }
    
    //Ieško mKeys masyve rakto reikšmės ir grąžina indeksą
    private int searchKeyInKeys(int key) {
        int lo = 0;
        int hi = mSize - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mKeys[mid] < key) {
                lo = mid + 1;
            } else if (mKeys[mid] > key) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    //Grąžina elementą pagal duotą raktą
    //@Override
    public E get(int key) {
        if (key == 0) {
            return (E) mValues[0];
        }
        int i = searchKeyInKeys(key);
        if (i < 0) {
            return null;
        } else {
            return (E) mValues[i];
        }
    }

    //=====
    //@Override
    public void remove(int key) {
        int i = searchKeyInKeys(key);
        if (i >= 0) {
            while (i < mSize) {
                mKeys[i] = mKeys[i + 1];
                mValues[i] = mValues[i + 1];
                i++;
            }
            mValues[i] = null;
            mKeys[i] = (int) (mKeys[i] & 0x00000000ffffffffL);
            mSize--;
        } else {
            System.out.println("=====Invalid key in remove(int key)=====");
        }
    }
    
    //Grąžina reikšmės indeksą sparse masyve
    public int indexOfValue(E value) {
        for (int i = 0; i < mSize; i++) {
            if (mValues[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    //Grąžina raktą pagal indeksą
    public int keyAt(int index) {
        if (index < mSize) {
            return mKeys[index];
        }
        return -1;
    }
    
    //Grąžina reikšmę pagal indeksą
    public E valueAt(int index) {
        if (index < mSize) {
            return (E) mValues[index];
        }
        return null;
    }

    //Ištrina reikšmę pagal indeksą
    public void removeAt(int index) {
        remove(mKeys[index]);
    }

    //Pakeičia reikšmę pagal indeksą
    public void setValueAt(int index, E value) {
        if (index < mSize) {
            mValues[index] = value;
        }
    }

    //Grąžina masyvo dydį
    //@Override
    public int size() {
        return mSize;
    }

    //Suformuoja sąrašą išvedimui
   // @Override
    public String toString() {
        if (mSize <= 0) {
            return "{Sąrašo ilgis: " + mSize + "\n}";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append("{Sąrašo ilgis: " + mSize + "\n");
        for (int i = 0; i < mSize; i++) {
            if (i > 0) {
                buffer.append(", \n");
            }
            buffer.append(mKeys[i] + "=" + mValues[i].toString());
        }
        buffer.append('}');
        return buffer.toString();
    }
    
    //Tikrina ar sąrašas tuščias
    //@Override
    public boolean isEmpty() {
        return mSize == 0;
    }
    
    //Išvalo sąrašą
    //@Override
    public void clear() {
        if (mSize == 0) {
            return;
        }
        while (mSize > 0) {
            remove(mKeys[0]);
        }
    }
    
    
}

