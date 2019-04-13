package nl.saxion.recipes.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.recipes.R;
import nl.saxion.recipes.model.Requirement;

public class RequirementList extends EditableLinearLayout {
    protected ViewGroup container;

    public RequirementList(final Context context, final boolean editMode, List<Requirement> requirementList) {
        super(context, editMode);
        setOrientation(VERTICAL);
        setId(R.id.requirements);

        container = findViewById(R.id.container);

        if (requirementList != null) {
            for(Requirement requirement : requirementList) {
                container.addView(
                        new RequirementView(context, editMode,
                                requirement.getAmount(), requirement.getName()));
            }
        }

        if(!editMode) findViewById(R.id.add).setVisibility(GONE);

        findViewById(R.id.add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                container.addView(new RequirementView(context, editMode));
            }
        });
    }

    @Override
    protected int getEditLayoutId() {
        return R.layout.compound_requirement_list;
    }

    @Override
    protected int getViewLayoutId() {
        return R.layout.compound_requirement_list;
    }

    @Override
    public boolean validate() {
        for(RequirementView requirementView : getRequirementViews()) {
            if (!requirementView.validate()) return false;
        }

        return true;
    }

    public List<RequirementView> getRequirementViews() {
        ViewGroup container = findViewById(R.id.container);
        List<RequirementView> requirementViews = new ArrayList<>();

        for(int i = 0; i < container.getChildCount(); i++) {
            requirementViews.add((RequirementView)container.getChildAt(i));
        }

        return requirementViews;
    }
}
