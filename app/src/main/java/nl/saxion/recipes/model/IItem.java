package nl.saxion.recipes.model;

import android.graphics.Bitmap;
import android.net.Uri;

public interface IItem {
    String id = null;
    String name = null;
    Uri imageBitmap = null;
    String placeholder = null;

    String getId();
    void setId(String image);
    String getName();
    void setName(String name);
    Bitmap getImageBitmap();
    void setImageBitmap(Bitmap imageBitmap);
    String getPlaceholder();
}
