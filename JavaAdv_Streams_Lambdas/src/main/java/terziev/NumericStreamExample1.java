/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terziev;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

/**
 *
 * @author terziev
 */
public class NumericStreamExample1 {

    public static void main(String[] args) {

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

        System.out.println("calculate the sum of calories in the menu");
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)//extracts all the calories from each dish and return IntStream
                .sum();
        System.out.println(calories);
        System.out.println("===========================================");
        //========================================
        //DEFAULT VALUES: OPTIONALINT
        System.out.println("find the max number of calories in the menu");
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        System.out.println(maxCalories.getAsInt());
        
        
        
        
    }
}
