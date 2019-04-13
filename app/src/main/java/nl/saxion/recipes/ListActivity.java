package nl.saxion.recipes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import nl.saxion.recipes.data.ItemAdapter;

public abstract class ListActivity<T> extends AppCompatActivity {
    protected abstract T getItems();
    /**
     * @return The class used for instantiating the activity for adding a new item, used for the FAB-button.
     */
    protected abstract Class getAddClass();
    private ItemAdapter adapter;
    protected abstract ItemAdapter getAdapter();

    /**
     * Inflates the list layout and sets the RecyclerView adapter,
     * and onClick listener or the add FAB-button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        adapter = getAdapter();
        ((RecyclerView)findViewById(R.id.list)).setAdapter(adapter);

        final Context context = this;

        findViewById(R.id.list_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(context);
            }
        });
    }

    /**
     * onClick listener for add FAB-button.
     */
    protected void add(Context context) {
        startActivity(new Intent(context, getAddClass()));
    }

    /**
     * Update adapter when starting and returning to the Activity.
     */
    @Override
    protected void onStart() {
        super.onStart();
        adapter = getAdapter();
        ((RecyclerView)findViewById(R.id.list)).setAdapter(adapter);
    }
}
