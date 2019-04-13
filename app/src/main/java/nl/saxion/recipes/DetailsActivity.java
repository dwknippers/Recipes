package nl.saxion.recipes;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public abstract class DetailsActivity extends ItemActivity implements Validateable {
    /**
     * ID Used for referencing the image pick Intent Activity result.
     */
    public static final int PICK_IMAGE = 0;
    /**
     * Stores the bitmap returned by the image pick flow.
     */
    protected Bitmap bitmap;
    protected boolean editMode = false;
    protected boolean newMode = false;
    protected abstract void restoreState();
    protected abstract Class getEditClass();
    protected abstract Class getListClass();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getItemId() != null) {
            restoreState();
        } else {
            initializeNewState();
            newMode = true;
        }

        if (editMode) {
            findViewById(R.id.list_details_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!validate()) return;
                    update();
                    finish();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!editMode) restoreState();
    }

    protected void initializeNewState() { }

    /**
     * Performs specific actions for updating item.
     */
    public abstract void update();

    /**
     * Performs specific actions for deleting item.
     */
    public abstract void delete();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (newMode) return true;

        getMenuInflater().inflate(!editMode ?
                R.menu.toolbar_menu_details :
                R.menu.toolbar_menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            ItemActivity.startItemActivity(this, getItem(), getEditClass());
        } else if (item.getItemId() == R.id.action_remove) {
            new AlertDialog.Builder(this)
                    .setTitle("Remove")
                    .setMessage("Are you sure you want to remove?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            delete();
                            finish();
                            Intent intent = new Intent();
                            if (getParentItemId() != null) {
                                intent.putExtra(PARENT_ITEM_ID, getParentItemId());
                            }
                            intent.setClass(getBaseContext(), getListClass());
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        return true;
    }

    public boolean validate() {
        TextView textView = findViewById(R.id.item_name);
        if (textView.getText().toString().equals("")) {
            textView.setError(getString(R.string.name_required));
            return false;
        }
        return true;
    }

    /**
     * OnClick handler of ImageView for selecting a new image.
     */
    public void selectImage(View view) {
        Intent imageIntent = new Intent(Intent.ACTION_PICK);
        imageIntent.setType("image/*");
        startActivityForResult(imageIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == AppCompatActivity.RESULT_OK) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ImageView imageView = findViewById(R.id.preview_image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
