package nl.saxion.recipes.data;

import android.content.Context;

import nl.saxion.recipes.R;
import nl.saxion.recipes.activities.recipe.RecipeDetailsActivity;
import nl.saxion.recipes.activities.recipe.RecipeEditActivity;
import nl.saxion.recipes.model.IItem;
import nl.saxion.recipes.model.Recipe;
import nl.saxion.recipes.views.RecipeAttributeIcons;

public class RecipeItemAdapter<T> extends ItemAdapter<T> {
    private String dishId;

    public RecipeItemAdapter(Context context, T items, String dishId) {
        super(context, items, RecipeEditActivity.class, RecipeDetailsActivity.class);
        this.dishId = dishId;
    }

    @Override
    public int getTemplate() {
        return R.layout.recipe_list_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        Recipe recipe = (Recipe)getItem(position);
        ((RecipeAttributeIcons)holder.view.findViewById(R.id.attributes))
                .setValues(recipe.getTime(), recipe.getServings(), recipe.getDifficulty());
    }
}
