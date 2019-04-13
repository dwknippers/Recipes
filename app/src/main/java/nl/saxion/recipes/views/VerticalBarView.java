package nl.saxion.recipes.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import nl.saxion.recipes.R;

public class VerticalBarView extends View {
    public static int MAX_PROGRESS = 100;
    public float DIVIDED_HEIGHT;
    int progress = 50;
    Paint paint;

    public VerticalBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setColor(context.getColor(R.color.gray));

        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.RecipeCustomView);
            MAX_PROGRESS = ta.getInt(R.styleable.RecipeCustomView_max_progress, MAX_PROGRESS);
            ta.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStrokeWidth(10);
        canvas.drawRect(0, getHeight(), getWidth(), getHeight()-getProgressHeight(), paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        DIVIDED_HEIGHT = h / (float)MAX_PROGRESS;
    }

    public void setProgress(int progress) {
        this.progress = progress > MAX_PROGRESS ? MAX_PROGRESS : progress;
    }

    public int getProgressHeight() {
        return Math.round(progress * DIVIDED_HEIGHT);
    }
}
