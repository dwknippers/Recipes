package nl.saxion.recipes.model;

import java.util.ArrayList;
import java.util.List;

public class Dish extends Item {
    private List<Recipe> recipes = new ArrayList<>();

    public Dish() {}

    public Dish(String name, String placeholder) {
        super(name, placeholder);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
