package nl.saxion.recipes.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import nl.saxion.recipes.R;
import nl.saxion.recipes.Validateable;

public abstract class EditableLinearLayout extends LinearLayout implements Validateable {
    protected boolean isEditMode = false;
    protected abstract int getEditLayoutId();
    protected abstract int getViewLayoutId();

    public EditableLinearLayout(Context context, boolean isEditMode) {
        super(context);

        this.isEditMode = isEditMode;

        inflateLayout(isEditMode);
    }

    public EditableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RecipeCustomView);
            isEditMode = ta.getBoolean(R.styleable.RecipeCustomView_edit, false);
            ta.recycle();
        }

        inflateLayout(isEditMode);
    }

    private void inflateLayout(boolean isEditMode) {
        if (isEditMode) {
            inflate(getContext(), getEditLayoutId(), this);
        } else {
            inflate(getContext(), getViewLayoutId(), this);
        }
    }
}
