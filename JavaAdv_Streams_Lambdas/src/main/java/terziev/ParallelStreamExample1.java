/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package terziev;

import java.util.stream.Stream;

/**
 *
 * @author terziev
 */
public class ParallelStreamExample1 {
    
    public static void main(String[] args) {
        
        System.out.println("PC info! : ");
        System.out.println("CPU model: Intel Core i5-3320M CPU @ 2.60 GHz");
        System.out.println("Available processors (cores): " + 
        Runtime.getRuntime().availableProcessors());
        
        System.out.println("==========================================================");
        measureTests(10, 30); //10 tests with 30 fib nums
        measureTests(10, 100); //10 tests with 100 fib nums
        
        System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
        
        measureTests(20, 30); //20 tests with 30 fib nums
        measureTests(20, 100); //20 tests with 100 fib nums
        
    }
    
    public static void generateFibSeq(int fibNums){
        
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(fibNums)
                .map(t -> t[0])
                .forEach(n -> System.out.print(n + ", "));
        System.out.println("");
        
    }
    
    public static void generateFibPar(int fibNums){
        
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .parallel()
                .limit(fibNums)
                .map(t -> t[0])
                .forEach(n -> System.out.print(n + ", "));
        System.out.println("");
    }
    
    public static long measureSeq(int fibNums){
        
        long start = System.nanoTime();
        generateFibSeq(fibNums);
        long end = System.nanoTime();
        long res = (end - start);
        return res;
    }
    
    public static long measurePar(int fibNums){
        
        long start = System.nanoTime();
        generateFibPar(fibNums);
        long end = System.nanoTime();
        long res = (end - start);
        return res;
    }
    
    public static void measureTests(int numTests, int fibNums){
        
        System.out.println("Comparing sequential stream performance vs parallel Stream");
        System.out.println("----------------------------------------");
        System.out.println("Number of tests: " + numTests);
        System.out.println("Amount of fibonacci numbers: " + fibNums);
        
        long tempSum1 = 0;
        
        for (int i = 0; i < numTests; i++) {
            long tempRes = measureSeq(fibNums);
            tempSum1 += tempRes;
        }
        long avgSeq = tempSum1/(long)numTests;
        //convert to seconds 
        double seconds = (double)avgSeq / 1000000000.0;
        System.out.println("Time to generate " + fibNums + " fibonacci numbers using sequential stream: " + avgSeq + " nanoseconds" + "\n in seconds: " + seconds);
        System.out.println("---------------------------------------------------------------");
        
        long tempSum2 = 0;
        
        for (int i = 0; i < numTests; i++) {
            long tempRes = measurePar(fibNums);
            tempSum2 += tempRes;
        }
        long avgPar = tempSum2/(long)numTests;
        
        double seconds2 = (double)avgPar / 1000000000.0;
        System.out.println("Time to generate " + fibNums + " fibonacci numbers using parallel stream: " + avgPar + " nanoseconds" + "\n in seconds: " + seconds2);
        System.out.println("=====================================================================================");
        
        if(avgSeq > avgPar){
            long dif = avgSeq - avgPar;
            double seconds3 = (double)dif / 1000000000.0;
            System.out.println("Parallel stream is faster with: " + dif + " nanoseconds" + "\n in seconds: " + seconds3);
        }else{
            long dif = avgPar - avgSeq;
            double seconds4 = (double)dif / 1000000000.0;
            System.out.println("Sequential stream is faster with: " + dif + " nanoseconds" + "\n in seconds: " + seconds4);
        }
        System.out.println("");
        
    }
}
