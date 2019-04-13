package nl.saxion.recipes.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import nl.saxion.recipes.R;

public class RecipeAttributeIcons extends EditableLinearLayout {
    Map<Integer, TextView> textViews;

    @Override
    protected int getEditLayoutId() {
        return R.layout.compound_recipe_attribute_icons_edit;
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.compound_recipe_attribute_icons;
    }

    public RecipeAttributeIcons(Context context, AttributeSet attrs) {
        super(context, attrs);

        textViews = new HashMap<>(3);
        textViews.put(R.id.time, (TextView)findViewById(R.id.time));
        textViews.put(R.id.servings, (TextView)findViewById(R.id.servings));
        textViews.put(R.id.difficulty, (TextView)findViewById(R.id.difficulty));
    }

    public void setValues(int time, int servings, int difficulty) {
        textViews.get(R.id.time).setText(String.valueOf(time));
        textViews.get(R.id.servings).setText(String.valueOf(servings));
        textViews.get(R.id.difficulty).setText(String.valueOf(difficulty));

        if (!isEditMode) {
            ((VerticalBarView)findViewById(R.id.vertical_time_bar)).setProgress(time);
            ((VerticalBarView)findViewById(R.id.vertical_servings_bar)).setProgress(servings);
            ((VerticalBarView)findViewById(R.id.vertical_difficulty_bar)).setProgress(difficulty);
        }
    }

    public Map<String, Integer> getValues() {
        final int time = Integer.valueOf(textViews.get(R.id.time).getText().toString());
        final int servings = Integer.parseInt(textViews.get(R.id.servings).getText().toString());
        final int difficulty = Integer.parseInt(textViews.get(R.id.difficulty).getText().toString());

        return new HashMap<String, Integer>() {{
            put("time", time);
            put("servings", servings);
            put("difficulty", difficulty);
        }};
    }

    @Override
    public boolean validate() {
        for(TextView textView : textViews.values()) {
            if (textView.getText().toString().equals("")) {
                textView.setError(getContext().getString(R.string.required));
                return false;
            }
        }

        return true;
    }
}
