package nl.saxion.recipes.data;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import nl.saxion.recipes.ItemActivity;
import nl.saxion.recipes.R;
import nl.saxion.recipes.model.IItem;

public abstract class ItemAdapter<ItemsType> extends RecyclerView.Adapter<ItemAdapter.ViewHolder>  {
    private ItemsType items;
    protected Class detailsActivity;
    protected Class navigateActivity;

    protected Context context;

    /**
     * @return The template to use for inflating new items.
     */
    public abstract int getTemplate();

    public ItemAdapter(Context context, ItemsType items, Class detailsActivity, Class navigateActivity) {
        this.context = context;
        this.items = items;
        this.detailsActivity = detailsActivity;
        this.navigateActivity = navigateActivity;
    }

    public IItem getItem(int position) {
        if (items instanceof Map) {
            IItem[] listItems = ((HashMap<String, IItem>)items).values().toArray(new IItem[0]);
            Arrays.sort(listItems);
            return listItems[position];
        } else if (items instanceof ArrayList) {
            return ((ArrayList<IItem>)items).get(position);
        } else {
            throw new InvalidParameterException();
        }
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(getTemplate(), parent, false);
        return new ViewHolder(itemView);
    }

    public static void setImageView(Context context, IItem item, ImageView imageView) {
        if (item.getImageBitmap() == null && item.getPlaceholder() != null) {
            InputStream inputStream = null;
            try {
                inputStream = context.getAssets().open(item.getPlaceholder());
                Drawable d = Drawable.createFromStream(inputStream, null);
                imageView.setImageDrawable(d);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            imageView.setImageBitmap(item.getImageBitmap());
        }
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        final IItem item = getItem(position);

        holder.name.setText(item.getName());
        setImageView(context, item, holder.thumbnail);

        registerEventHandlers(holder, item);
    }

    /**
     * The action to perform to for the details action.
     * @param item The item to use for starting the item Activity.
     */
    public void details(IItem item) {
        ItemActivity.startItemActivity(context, item, detailsActivity);
    }

    /**
     * The action to perform to for the navigate action.
     * @param item The item to use for starting the item Activity.
     */
    public void navigate(IItem item) {
        ItemActivity.startItemActivity(context, item, navigateActivity);
    }

    protected void registerEventHandlers(ItemAdapter.ViewHolder holder, final IItem item) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(item);
            }
        });

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                details(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (items instanceof Map) {
            return ((HashMap<String, IItem>)items).values().size();
        } else if (items instanceof  ArrayList) {
            return ((ArrayList<String>)items).size();
        } else {
            throw new InvalidParameterException();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;
        public View view;
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            thumbnail = itemView.findViewById(R.id.item_thumbnail);
            view = itemView;
        }
    }
}
