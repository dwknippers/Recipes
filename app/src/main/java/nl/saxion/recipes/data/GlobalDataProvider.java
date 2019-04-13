package nl.saxion.recipes.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.saxion.recipes.model.Dish;
import nl.saxion.recipes.model.IItem;
import nl.saxion.recipes.model.Requirement;
import nl.saxion.recipes.model.Recipe;

public class GlobalDataProvider {
    public static Map<String, IItem> dishes = new HashMap<>();
    public static Map<String, IItem> recipies = new HashMap<>();

    static {
        final Dish mainCourse = new Dish("Main Course","Main Course.jpg");
        List<Dish> dishList = new ArrayList<Dish>() {{
            add(new Dish("Appetizer", "Appetizer.jpg"));
            add(mainCourse);
            add(new Dish("Dessert", "Dessert.jpg"));
            add(new Dish("Lunch", "Lunch.jpg"));
            add(new Dish("Breakfast", "Breakfast.jpg"));
            add(new Dish("Snack", "Snack.jpg"));
        }};

        for (Dish dish : dishList) {
            dishes.put(dish.getId(), dish);
        }

        List<Recipe> recipeList = new ArrayList<Recipe>() {{
            add(new Recipe("Fish tacos with salsa picada", "Main Course.jpg", new ArrayList<Requirement>() {{
                add(new Requirement("maseca harina corn flour", "150g"));
                add(new Requirement("flour + 4 tbsp", "200g"));
                add(new Requirement("Mexican Beer", "200ml"));
                add(new Requirement("Egg", "1"));
                add(new Requirement("Salt"));
                add(new Requirement("ground chipotle", "1tbsp"));
                add(new Requirement("garlic powder", "1tbsp"));
                add(new Requirement("cod tails with skin", "500g"));
                add(new Requirement("tomatoes", "2-3"));
                add(new Requirement("chili pepper", "2-4 red"));
                add(new Requirement("red union", "1"));
                add(new Requirement("coriander", "6 twigs"));
                add(new Requirement("mint", "3 twigs"));
                add(new Requirement("lemon", "1"));
                add(new Requirement("sugar"));
                add(new Requirement(" tortilla press"));
                add(new Requirement("frying pan with oil"));
            }
            },55, 1,
                    "1. Mix the maseca harina with a splash of water. Knead briefly into a smooth dough. If it is too dry, update a little extra water. Divide the dough into 4 balls and cover with plastic wrap or a damp tea towel. Set a minute or 10 apart.\n" +
                            "\n" +
                            "2. For the salsa, cut the tomatoes into cubes. Cut the peppers into rings. Peel and chop the onion. Finely chop the coriander (including the stems) and mint leaves. Grate the zest of the lime. Mix the tomatoes, peppers, onion, coriander, mint and lime zest. Season with salt, lime juice and a little sugar.\n" +
                            "\n" +
                            "3. Cover the tortilla press with two layers of strong plastic wrap. Flatten a ball of dough and carefully peel it from the plastic.\n" +
                            "\n" +
                            "4. Heat the frying pan (without fat) and fry the soft tacos on both sides for about 3 minutes. Store in a tea towel. Press and bake the remaining tortillas.\n" +
                            "\n" +
                            "5. Meanwhile heat the frying oil to 180 ° C.\n" +
                            "\n" +
                            "6. For the fish fritters, beat a batter of 200 grams of flour, the beer and the egg. Season with salt, chipotle and garlic powder.\n" +
                            "\n" +
                            "7. Cut the fish into thick strips, sprinkle with salt. Put the 4 tablespoons of flour in a bowl. First get the fish through the flour and then through the beer batter. Then slowly lower them into the frying fat in portions. Don't bake too many at the same time because they will stick together. Fry them in 3-4 minutes until golden brown. Drain on kitchen paper and sprinkle with some salt.\n" +
                            "\n" +
                            "8. Serve the soft tacos with the fish fritters and salsa picada on top. Drizzle some lime juice over it if desired.", 4));
        }};

        for (Recipe recipe : recipeList) {
            recipies.put(recipe.getId(), recipe);
        }

        mainCourse.getRecipes().add(recipeList.get(0));
    }
}
