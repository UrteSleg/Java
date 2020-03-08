package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.gui.ValidationException;
import edu.ktu.ds.lab2.utils.*;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class SimpleBenchmark {

    public static final String FINISH_COMMAND = "                       ";
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("edu.ktu.ds.lab2.gui.messages");

    private static final String[] BENCHMARK_NAMES = {"addBstRec", "addBstIte", "addAvlRec", "removeBst"};
    private static final int[] COUNTS = {10000, 20000, 40000, 80000};

    private final Timekeeper timeKeeper;
    private final String[] errors;

    private final SortedSet<Car> cSeries = new BstSet<>(Car.byPrice);
    private final SortedSet<Car> cSeries2 = new BstSetIterative<>(Car.byPrice);
    private final SortedSet<Car> cSeries3 = new AvlSet<>(Car.byPrice);

    /**
     * For console benchmark
     */
    public SimpleBenchmark() {
        timeKeeper = new Timekeeper(COUNTS);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    /**
     * For Gui benchmark
     *
     * @param resultsLogger
     * @param semaphore
     */
    public SimpleBenchmark(BlockingQueue<String> resultsLogger, Semaphore semaphore) {
        semaphore.release();
        timeKeeper = new Timekeeper(COUNTS, resultsLogger, semaphore);
        errors = new String[]{
                MESSAGES.getString("badSetSize"),
                MESSAGES.getString("badInitialData"),
                MESSAGES.getString("badSetSizes"),
                MESSAGES.getString("badShuffleCoef")
        };
    }

    public static void main(String[] args) {
        executeTest();
    }

    public static void executeTest() {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        Ks.out("Greitaveikos tyrimas:\n");
        //new SimpleBenchmark().startBenchmark();
        new SimpleBenchmark().startGreitaveika1();
        new SimpleBenchmark().startGreitaveika2();
    }
    
    public static void startGreitaveika1()
    {
         Ks.oun("contains greitaveika");
        Ks.oun("El kiekis, TreeSet <-> HashSet ");
        for(int i = 1; i<10;i++)
        {
            TreeSet<Integer> t1 = new TreeSet<>();
            HashSet<Integer> t2 = new HashSet<>();
            
            Random rand = new Random();
            
            for (int j=0; j < i* 10000; j++)
            {
                int t = rand.nextInt(50000);
                t1.add(t);
                t2.add(t);
            }
            
            Integer a = rand.nextInt(50000);
            long time1 = System.nanoTime();
            t1.contains(a);
            long time2 = System.nanoTime();
            
            long time3 = System.nanoTime();
            t2.contains(a);
            long time4 = System.nanoTime();
            
            Ks.oun("Duomenu kiekis: " + 10000 * i + ", TreeSet: " + (time2 - time1) + "ns, Hashset: " + (time4 - time3) + "ns");
        }
    }
    
    public void startGreitaveika2()
    {
        Ks.oun("remove greitaveika");
        Ks.oun("El kiekis, TreeSet <-> HashSet ");
        for (int i = 1; i <= 10; i++) {
            TreeSet<Integer> t1 = new TreeSet<>();
            HashSet<Integer> t2 = new HashSet<>();

            Random rand = new Random();

            for (int j = 0; j < i * 10000; j++){
                int t = rand.nextInt(50000);
                t1.add(t);
                t2.add(t);
            }
            
            List<Integer> arr = new ArrayList<Integer>(1000);
            
            for (int j = 0; j < 1000; j++) {
                arr.add(rand.nextInt(50000));
            }
            
            long time1 = System.nanoTime();
            t1.remove(rand.nextInt(50000));
            long time2 = System.nanoTime();
            
            long time3 = System.nanoTime();
            t2.remove(rand.nextInt(50000));
            long time4 = System.nanoTime();
            
            Ks.oun("Duomenu kiekis" + 10000 * i + " " + (time2-time1) + "ns <-> " + (time4-time3) + "ns");
    }
        
  }
    public void startBenchmark() {
        try {
            benchmark();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void benchmark() throws InterruptedException {
        try {
            for (int k : COUNTS) {
                Car[] cars = new CarsGenerator().generateShuffle(k, 1.0);
                cSeries.clear();
                cSeries2.clear();
                cSeries3.clear();
                timeKeeper.startAfterPause();

                timeKeeper.start();
                Arrays.stream(cars).forEach(cSeries::add);
                timeKeeper.finish(BENCHMARK_NAMES[0]);

                Arrays.stream(cars).forEach(cSeries2::add);
                timeKeeper.finish(BENCHMARK_NAMES[1]);

                Arrays.stream(cars).forEach(cSeries3::add);
                timeKeeper.finish(BENCHMARK_NAMES[2]);

                Arrays.stream(cars).forEach(cSeries::remove);

                timeKeeper.finish(BENCHMARK_NAMES[3]);
                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult(FINISH_COMMAND);
        } catch (ValidationException e) {
            if (e.getCode() >= 0 && e.getCode() <= 3) {
                timeKeeper.logResult(errors[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                timeKeeper.logResult(MESSAGES.getString("allSetIsPrinted"));
            } else {
                timeKeeper.logResult(e.getMessage());
            }
        }
    }
}
