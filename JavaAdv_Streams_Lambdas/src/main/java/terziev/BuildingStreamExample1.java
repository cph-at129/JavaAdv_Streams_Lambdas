/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terziev;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author terziev
 */
public class BuildingStreamExample1 {

    public static void main(String[] args) {

        System.out.println("Streams from values");
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        Stream<String> emptyStream = Stream.empty();//empty stream

        System.out.println("=========================================");
        System.out.println("Streams from arrays");
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();

        System.out.println("==========================================");
        System.out.println("Streams from files");
        System.out.println("find out the number of unique words in a file");
        long uniqueWords = 0;
        try (Stream<String> lines
                = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))//Generate a stream of words
                    .distinct()//remove duplicates
                    .count(); //count the number of unique words
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        System.out.println("Unique words: " + uniqueWords);

        System.out.println("==================================================");
        System.out.println("generate the first 30 of the fibonacci numbers by using the power of streams");
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(30)
                .map(t -> t[0])
                .forEach(n -> System.out.print(n + ", "));
        
        System.out.println("");
        System.out.println("==============================================");
        
        
        

    }
}
