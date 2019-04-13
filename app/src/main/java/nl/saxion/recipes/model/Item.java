package nl.saxion.recipes.model;

import android.graphics.Bitmap;

import java.util.UUID;

public class Item implements IItem, Comparable<IItem> {
    private String id;
    private String name;
    private Bitmap imageBitmap;
    protected String placeholder;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public Item(String name, Bitmap imageBitmap) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.imageBitmap = imageBitmap;
    }

    public Item(String name, String placeholder) {
        this(name, (Bitmap)null);
        this.placeholder = placeholder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Bitmap getImageBitmap() {
        return this.imageBitmap;
    }

    @Override
    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public int compareTo(IItem o) {
        return this.getName().compareTo(o.getName());
    }
}
