package nl.saxion.recipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import java.util.Map;

import nl.saxion.recipes.model.IItem;

public abstract class ItemActivity extends AppCompatActivity {
    /**
     * The String key used for passing the ID of the handled item used by startActivity.
     */
    public static final String ITEM_ID = "ITEM_ID";
    public static final String PARENT_ITEM_ID = "PARENT_ITEM_ID";

    /**
     * @return The collection to which the item belongs.
     */
    protected abstract Map<String, IItem> getItems();

    protected String getExtraString(String key) {
        if (getIntent().getExtras() == null) {
            return null;
        } else {
            return getIntent().getExtras().getString(key);
        }
    }

    protected String getItemId() {
        return getExtraString(ITEM_ID);
    }

    protected String getParentItemId() {
        return getExtraString(PARENT_ITEM_ID);
    }

    /**
     * @return The layout to be inflated for this Activity.
     */
    protected abstract int getContentId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
    }

    protected IItem getItem() {
        Map<String, IItem> items = getItems();
        if (items == null || getItemId() == null){
            return null;
        } else {
            return items.get(getItemId());
        }
    }

    /**
     * Creates and starts a new Activity with intent containing id of handled id.
     * @param context The context to be used.
     * @param item The item to pass the info about to the newly created activity.
     * @param activityClass The class of the Activity to be instantiated.
     */
    public static void startItemActivity(Context context, IItem item, Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.putExtra(ITEM_ID, item.getId());
        context.startActivity(intent);
    }
}
