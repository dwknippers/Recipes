package nl.saxion.recipes.activities.dish;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.Map;

import nl.saxion.recipes.DetailsActivity;
import nl.saxion.recipes.R;
import nl.saxion.recipes.data.GlobalDataProvider;
import nl.saxion.recipes.data.ItemAdapter;
import nl.saxion.recipes.model.Dish;
import nl.saxion.recipes.model.IItem;

public class DishDetailsActivity extends DetailsActivity {
    @Override
    protected int getContentId() {
        return R.layout.activity_dish_details;
    }

    @Override
    protected void restoreState() {
        IItem item = getItem();
        ImageView imageView = findViewById(R.id.preview_image);
        ItemAdapter.setImageView(this, item, imageView);
        ((TextView) findViewById(R.id.item_name)).setText(item.getName());
    }

    @Override
    protected Class getEditClass() {
        return DishEditActivity.class;
    }
    @Override
    protected Class getListClass() {
        return DishListActivity.class;
    }

    @Override
    public void update() {
        IItem item = getItem();
        Dish dish = item != null ? (Dish)item : new Dish();

        dish.setName(((TextView)findViewById(R.id.item_name)).getText().toString());
        if (bitmap != null) dish.setImageBitmap(bitmap);

        GlobalDataProvider.dishes.put(dish.getId(), dish);
    }

    @Override
    public void delete() {
        getItems().remove(getItemId());
    }

    @Override
    protected Map<String, IItem> getItems() {
        return GlobalDataProvider.dishes;
    }
}
