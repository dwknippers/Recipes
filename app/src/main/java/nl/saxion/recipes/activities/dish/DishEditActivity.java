package nl.saxion.recipes.activities.dish;

import android.os.Bundle;

import nl.saxion.recipes.R;

public class DishEditActivity extends DishDetailsActivity {
    @Override
    protected int getContentId() {
        return R.layout.activity_dish_edit;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        editMode = true;
        super.onCreate(savedInstanceState);
    }
}
