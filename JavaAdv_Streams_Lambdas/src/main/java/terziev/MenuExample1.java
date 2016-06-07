package terziev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class MenuExample1 {

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

        //=====    return the names of dishes that are low in calories, 
        //              sorted by number of calories using Java 7
        
        //1) filter the elements using an acumulator
        List<Dish> lowCaloricDishes = new ArrayList<>();//use a “garbage variable,” lowCaloricDishes!!!!
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        //2) sort the dishes with an anonymous class    
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        
        //3) process the sorted list to select the names of the dishes
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        System.out.println("Java 7: " + lowCaloricDishesName.toString());

        //=========================================================
        //=====    using Java 8 - Streams and Lambdas ==========
        List<String> lowCaloricDishesName2
                = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        lowCaloricDishesName2.forEach(d -> System.out.print(d + ", "));
        System.out.println("");
        //====   using Parallel Stream ==========
        List<String> lowCaloricDishesName3
                = menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        lowCaloricDishesName3.forEach(d -> System.out.print(d + ", "));
        System.out.println("");
    }

}
