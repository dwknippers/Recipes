package nl.saxion.recipes.activities.recipe;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.saxion.recipes.DetailsActivity;
import nl.saxion.recipes.R;
import nl.saxion.recipes.activities.dish.DishListActivity;
import nl.saxion.recipes.data.GlobalDataProvider;
import nl.saxion.recipes.data.ItemAdapter;
import nl.saxion.recipes.model.Dish;
import nl.saxion.recipes.model.IItem;
import nl.saxion.recipes.model.Recipe;
import nl.saxion.recipes.model.Requirement;
import nl.saxion.recipes.views.RecipeAttributeIcons;
import nl.saxion.recipes.views.RequirementList;
import nl.saxion.recipes.views.RequirementView;

public class RecipeDetailsActivity extends DetailsActivity {
    protected int getContentId() {
        return R.layout.activity_recipe_details;
    }

    @Override
    protected void restoreState() {
        Recipe item = (Recipe)getItem();
        ImageView imageView = findViewById(R.id.preview_image);
        ItemAdapter.setImageView(this, item, imageView);

        if(editMode) {
            ((TextView)findViewById(R.id.item_name)).setText(item.getName());
        } else {
            setTitle(item.getName());
        }

        if (item != null) {
            ((RecipeAttributeIcons)findViewById(R.id.attributes))
                    .setValues(item.getTime(), item.getServings(), item.getDifficulty());
        }

        ((TextView)findViewById(R.id.instructions)).setText(item.getInstructions());
        insertRequirementsList(item.getRequirements());
    }

    private void insertRequirementsList(List<Requirement> requirements) {
        View requirementListPlaceholder = findViewById(R.id.requirements);
        ViewGroup parent = ((ViewGroup)requirementListPlaceholder.getParent());
        int placeholderPosition = parent.indexOfChild(requirementListPlaceholder);
        parent.removeView(requirementListPlaceholder);
        parent.addView(new RequirementList(this, editMode, requirements), placeholderPosition);
    }

    @Override
    protected void initializeNewState() {
        insertRequirementsList(null);
    }

    @Override
    protected Class getEditClass() {
        return RecipeEditActivity.class;
    }
    @Override
    protected Class getListClass() {
        return DishListActivity.class;
    }

    @Override
    public void update() {
        Recipe item = (Recipe)getItem();

        List<RequirementView> requirementsViews =
                ((RequirementList)findViewById(R.id.requirements)).getRequirementViews();

        List<Requirement> requirements = new ArrayList<>(requirementsViews.size());

        for(RequirementView requirementView : requirementsViews) {
            requirements.add(requirementView.getRequirement());
        }

        Map<String, Integer> attributes = ((RecipeAttributeIcons)findViewById(R.id.attributes)).getValues();

        String instructions = ((TextView)findViewById(R.id.instructions)).getText().toString();

        Recipe recipe = item != null ? item : new Recipe();

        recipe.setName(((TextView)findViewById(R.id.item_name)).getText().toString());
        if (bitmap != null) recipe.setImageBitmap(bitmap);
        recipe.setRequirements(requirements);
        recipe.setTime(attributes.get("time"));
        recipe.setServings(attributes.get("servings"));
        recipe.setDifficulty(attributes.get("difficulty"));
        recipe.setInstructions(instructions);

        // add to dish -> recipes
        String dishId = getExtraString(PARENT_ITEM_ID);
        if (dishId != null) {
            // New recipe
            Dish dish = (Dish)GlobalDataProvider.dishes.get(dishId);
            dish.getRecipes().add(recipe);
            GlobalDataProvider.recipies.put(recipe.getId(), recipe);
        }
    }

    @Override
    public void delete() {
        Recipe recipe = (Recipe)getItem();

        getItems().remove(getItemId());

        for(IItem item : GlobalDataProvider.dishes.values()) {
            Dish dish = (Dish)item;
            dish.getRecipes().remove(recipe);
        }
    }

    @Override
    protected Map<String, IItem> getItems() {
        return GlobalDataProvider.recipies;
    }

    @Override
    public boolean validate() {
        if (!super.validate()) return false;

        TextView instructions = findViewById(R.id.instructions);
        if (instructions.getText().toString().equals("")) {
            instructions.setError(getString(R.string.required));
            return false;
        }

        return true;
    }
}
