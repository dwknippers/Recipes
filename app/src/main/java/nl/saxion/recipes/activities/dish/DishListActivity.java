package nl.saxion.recipes.activities.dish;

import nl.saxion.recipes.ListActivity;
import nl.saxion.recipes.data.DishItemAdapter;
import nl.saxion.recipes.data.GlobalDataProvider;
import nl.saxion.recipes.data.ItemAdapter;

public class DishListActivity<T> extends ListActivity<T> {
    @Override
    protected T getItems() {
        return (T)GlobalDataProvider.dishes;
    }

    @Override
    protected Class getAddClass() {
        return DishEditActivity.class;
    }

    @Override
    protected ItemAdapter getAdapter() {
        return new DishItemAdapter(this, getItems());
    }
}
