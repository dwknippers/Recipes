package nl.saxion.recipes.activities.recipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

import nl.saxion.recipes.ListActivity;
import nl.saxion.recipes.data.ItemAdapter;
import nl.saxion.recipes.data.GlobalDataProvider;
import nl.saxion.recipes.data.RecipeItemAdapter;
import nl.saxion.recipes.model.Dish;
import nl.saxion.recipes.model.IItem;

import static nl.saxion.recipes.ItemActivity.ITEM_ID;
import static nl.saxion.recipes.ItemActivity.PARENT_ITEM_ID;

public class RecipeListActivity<T> extends ListActivity<T> {
    private String dishId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dishId = getIntent().getExtras().getString(ITEM_ID);
        super.onCreate(savedInstanceState);
    }
    @Override
    protected T getItems() {
        return (T)new ArrayList<IItem>(((Dish)GlobalDataProvider.dishes.get(dishId)).getRecipes());
    }

    @Override
    protected Class getAddClass() {
        return RecipeEditActivity.class;
    }

    @Override
    protected ItemAdapter getAdapter() {
        return new RecipeItemAdapter(this, getItems(), dishId);
    }

    @Override
    protected void add(Context context) {
        Intent intent = new Intent(getBaseContext(), RecipeEditActivity.class);
        intent.putExtra(PARENT_ITEM_ID, dishId);
        context.startActivity(intent);
    }
}
