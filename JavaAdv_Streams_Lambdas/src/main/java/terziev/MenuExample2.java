package terziev;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

public class MenuExample2 {

    public static void main(String[] args) {

        //make a list
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
        System.out.println("Menu");
        System.out.println(menu.toString());

        //================================   Filtering ======================================
        System.out.println("=============================");
        System.out.println("Filtering with a predicate");
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)// ?????????????
                .collect(toList());
        System.out.println(vegetarianMenu.toString());

        System.out.println("===============================");
        System.out.println("Filtering unique elements");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct() //?????????
                .forEach(System.out::println);

        System.out.println("=================================");
        System.out.println("Truncating a stream");
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3) //???????????????
                .collect(toList());
        System.out.println(dishes.toString());

        System.out.println("==================================");
        System.out.println("Skipping elements");
        List<Dish> dishes2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes2.toString());

        System.out.println("====================================");
        System.out.println("Filter the first two meat dishes");
        menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .forEach(System.out::println);

        //===============================  Mapping ======================================================
        System.out.println("======================================");
        System.out.println("Applying a function to each element of a stream");
        List<Integer> dishNames = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNames.toString());

        System.out.println("========================================");
        System.out.println("return a list of the number of characters for each word");
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths.toString());

        System.out.println("===========================================");
        System.out.println("Flattening streams");
        System.out.println("return a list of all the unique characters for a list of words");
        List<String> words2 = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<String> uniqueCharacters
                = words2.stream()
                .map(w -> w.split(""))//Converts each word into an array of its individual letters
                .flatMap(Arrays::stream) //Flattens each generated stream into a single stream
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters.toString());

        System.out.println("==========================================");
        System.out.println("Given a list of numbers, how would you return a list of the square of each number");
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5); //should return [1, 4, 9, 16, 25]
        List<Integer> numsSquare = nums.stream()
                .map(d -> d * d)
                .collect(toList());
        System.out.println(numsSquare.toString());

        System.out.println("============================================");
        System.out.println("Given two lists of numbers, how would you return all pairs of numbers");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        System.out.println(numbers1.toString());
        System.out.println(numbers2.toString());
        List<int[]> pairs
                = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs.forEach(d -> System.out.println("[" + d[0] + ", " + d[1] + "]"));

        System.out.println("return only pairs whose sum is divisible by 3");
        List<int[]> pairs2 = numbers1.stream()
                .flatMap(i -> numbers2
                        .stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
        pairs2.forEach(d -> System.out.println("[" + d[0] + ", " + d[1] + "]"));
        System.out.println("");

        System.out.println("==============================================");
        System.out.println("Finding and matching");

        System.out.println("Checking to see if a predicate matches all elements");
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        } else if (menu.stream().allMatch(d -> d.getCalories() < 1000) || menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("The menu is healthy");
        }

        System.out.println("Finding a vegetarian dish");
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

        System.out.println("Finding the first element");
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst() // 9
                .ifPresent(System.out::println);

        //================  Reducing ========================================
        
        //sum the elements of a list of numbers using a for-each loop -  Java 7
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }
        
        //Java 8
        int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
        
        System.out.println("===========================");
        System.out.println("Maximum and minimum");
        
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        max.ifPresent(n -> System.out.println("Max number: " + n));
        min.ifPresent(n -> System.out.println("Min number: " + n));
        
        System.out.println("===================================");
        System.out.println("Number of dishes in the menu");
        int sum3 = menu
                .stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        
        System.out.println(sum3);
        //OR
        long sum4 = menu.stream().count();
        
        
                
                

    }

}
