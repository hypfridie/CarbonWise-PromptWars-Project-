package com.carbonwise.app.ui.onboarding;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.carbonwise.app.R;

public class StepIndicatorView extends View {

    private int stepCount = 4;
    private int currentStep = 0;
    private final Paint activePaint;
    private final Paint inactivePaint;

    public StepIndicatorView(Context context) {
        this(context, null);
    }

    public StepIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StepIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        activePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        activePaint.setColor(getResources().getColor(R.color.emerald_green, null));

        inactivePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        inactivePaint.setColor(getResources().getColor(R.color.card_stroke, null));
    }

    public void setStepCount(int count) {
        this.stepCount = count;
        invalidate();
    }

    public void setCurrentStep(int step) {
        this.currentStep = step;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        float segmentWidth = width / stepCount;
        float radius = height / 2f;

        for (int i = 0; i < stepCount; i++) {
            float left = i * segmentWidth + 4;
            float right = (i + 1) * segmentWidth - 4;
            float top = 0;
            float bottom = height;
            float cornerRadius = radius;

            Paint paint = i <= currentStep ? activePaint : inactivePaint;
            canvas.drawRoundRect(left, top, right, bottom, cornerRadius, cornerRadius, paint);
        }
    }
}
