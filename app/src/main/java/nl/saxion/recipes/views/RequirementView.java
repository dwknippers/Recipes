package nl.saxion.recipes.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.saxion.recipes.R;
import nl.saxion.recipes.model.Requirement;

public class RequirementView extends EditableLinearLayout {
    public RequirementView(Context context, boolean editMode) {
        this(context, editMode, "", "");
    }

    public RequirementView(Context context, boolean editMode, String amount, String name) {
        super(context, editMode);

        TextView amountTextView = findViewById(R.id.amount);
        if (amount == null) {
            removeView(amountTextView);
        } else {
            amountTextView.setText(amount);
        }
        ((TextView)findViewById(R.id.name)).setText(name);

        if (editMode) {
            findViewById(R.id.delete).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete();
                }
            });
        }
    }

    @Override
    protected int getEditLayoutId() {
        return R.layout.compound_requirement_edit;
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.compound_requirement;
    }

    public Requirement getRequirement() {
        String amount = ((TextView)findViewById(R.id.amount)).getText().toString();
        String name = ((TextView)findViewById(R.id.name)).getText().toString();

        if (amount.equals("")) {
            return new Requirement(name);
        } else {
            return new Requirement(name, amount);
        }
    }

    @Override
    public boolean validate() {
        TextView textView = findViewById(R.id.name);

        if (textView.getText().toString().equals("")) {
            textView.setError(getContext().getString(R.string.required));
            return false;
        }

        return true;
    }

    public void delete() {
        ((ViewGroup)this.getParent()).removeView(this);
    }
}
