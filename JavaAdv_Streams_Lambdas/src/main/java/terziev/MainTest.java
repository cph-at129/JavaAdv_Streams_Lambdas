/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terziev;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author terziev
 */
public class MainTest {

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

        Optional<Dish> d
                = menu.stream()
                .filter(dish -> dish.isVegetarian())
                .findAny();
        Optional<Dish> d2
                = menu.stream()
                .filter(dish -> dish.isVegetarian())
                .findFirst();

        System.out.println("Found dish 2 " + d.get().getName());

        //implement the methods
        //write explanations 
    }
}
