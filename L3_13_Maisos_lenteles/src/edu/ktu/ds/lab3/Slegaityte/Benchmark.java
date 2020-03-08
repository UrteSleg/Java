/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab3.Slegaityte;

import edu.ktu.ds.lab3.demo.SimpleBenchmark;
import edu.ktu.ds.lab3.demo.Timekeeper;
import edu.ktu.ds.lab3.gui.ValidationException;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.Ks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Benchmark
{
    private edu.ktu.ds.lab3.utils.HashMap<String, String> ktuMap;
    private java.util.HashMap<String, String> javaMap;

    private final Timekeeper timeKeeperPut;
    private final Timekeeper timeKeeperRemove;
    private final int[] counts_put = {1000, 5000, 10000, 20000};
    private final int[] counts_remove = {1000, 5000, 10000, 20000, 40000, 80000};

    private Random rnd;

    public Benchmark()
    {
        ktuMap = new HashMap<>();
        javaMap = new java.util.HashMap<>();

        timeKeeperPut = new Timekeeper(counts_put);
        timeKeeperRemove = new Timekeeper(counts_remove);

        rnd = new Random();
        rnd.setSeed(2019);
    }

    private List<String> readFile()
    {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get("data/zodynas.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    private void initializeMaps(List<String> text)
    {
        ktuMap = new HashMap<>();
        javaMap = new java.util.HashMap<>();
        for (String word: text)
        {
            ktuMap.put(word, word);
            javaMap.put(word, word);
        }
    }

    private List<String> initializeRandom(List<String> full, int count)
    {
        Collections.shuffle(full);
        return full.subList(0, count);
    }

    private void benchmark() throws InterruptedException
    {
        benchmarkPut();
        benchmarkRemove();
    }

    private void benchmarkPut() throws InterruptedException
    {
        try
        {
            for (int count : counts_put)
            {
                List<String> words = readFile();
                initializeMaps(words);
                List<String> randomWords = initializeRandom(words, count);

                timeKeeperPut.startAfterPause();
                timeKeeperPut.start();

                for (String word: randomWords)
                {
                    ktuMap.put(word, word);
                }
                timeKeeperPut.finish("PutKtu");

                for (String word: randomWords)
                {
                    javaMap.put(word, word);
                }
                timeKeeperPut.finish("PutJava");
                timeKeeperPut.seriesFinish();
            }
            timeKeeperPut.logResult("");
        }
        catch (ValidationException e)
        {
            timeKeeperPut.logResult(e.getMessage());
        }
    }

    private void benchmarkRemove() throws InterruptedException
    {
        try
        {
            for (int count : counts_remove)
            {
                List<String> words = readFile();
                initializeMaps(words);
                List<String> randomWords = initializeRandom(words, count);

                timeKeeperRemove.startAfterPause();
                timeKeeperRemove.start();

                for (String word : randomWords)
                {
                    ktuMap.remove(word);
                }
                timeKeeperRemove.finish("remKtu");

                for (String word : randomWords)
                {
                    javaMap.remove(word);
                }
                timeKeeperRemove.finish("remJava");
                timeKeeperRemove.seriesFinish();
            }
            timeKeeperRemove.logResult("");
        }
        catch (ValidationException e)
        {
            timeKeeperRemove.logResult(e.getMessage());
        }
    }

    private void run()
    {
        try
        {
            benchmark();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    public static void main(String... args)
    {
        Ks.oun("Greitaveikos pradžia:");
        new Benchmark().run();
        Ks.oun("Pabaiga");
    }
}