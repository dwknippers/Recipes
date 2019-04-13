package nl.saxion.recipes.activities.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;

import nl.saxion.recipes.R;
import nl.saxion.recipes.Validateable;

public class RecipeEditActivity extends RecipeDetailsActivity {
    @Override
    protected int getContentId() {
        return R.layout.activity_recipe_edit;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        editMode = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean validate() {
        if (!((Validateable)findViewById(R.id.attributes)).validate()) return false;

        if (!super.validate()) return false;

        if (!((Validateable)findViewById(R.id.requirements)).validate()) return false;

        return true;
    }
}
