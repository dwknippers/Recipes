package nl.saxion.recipes.data;

import android.content.Context;

import nl.saxion.recipes.activities.dish.DishDetailsActivity;
import nl.saxion.recipes.R;
import nl.saxion.recipes.activities.dish.DishEditActivity;
import nl.saxion.recipes.activities.recipe.RecipeListActivity;

public class DishItemAdapter<T> extends ItemAdapter<T> {
    public DishItemAdapter(Context context, T items) {
        super(context, items, DishDetailsActivity.class, RecipeListActivity.class);
    }

    @Override
    public int getTemplate() {
        return R.layout.dish_list_item;
    }
}
