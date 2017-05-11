package com.example.xh.customball;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


public class WaveBall extends View implements View.OnClickListener {
    private int radius;
    private Paint RectPaint;
    private Paint CirclePaint;
    private Canvas BitmapCanvas;
    private Paint fontPaint;
    Bitmap bitmap;

    public WaveBall(Context context) {
        this(context, null);
    }

    public WaveBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        radius = dp2px(80);
        RectPaint = new Paint();
        RectPaint.setStyle(Paint.Style.FILL);
        RectPaint.setAntiAlias(true);
        RectPaint.setColor(Color.GREEN);
        bitmap = Bitmap.createBitmap(radius * 2, radius * 2, Bitmap.Config.ARGB_8888);
        BitmapCanvas = new Canvas(bitmap);
        BitmapCanvas.drawColor(Color.GREEN);

        CirclePaint = new Paint();
        CirclePaint.setAntiAlias(true);
        CirclePaint.setColor(Color.RED);
        CirclePaint.setStyle(Paint.Style.FILL);

        fontPaint = new Paint();
        fontPaint.setAntiAlias(true);
        fontPaint.setColor(Color.BLACK);
        fontPaint.setTextSize(sp2px(18));
        BitmapCanvas.drawCircle(radius, radius, radius, CirclePaint);

        setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(radius * 2, radius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectPaint.reset();

        String text = 0 + "%";
        float textWidth = fontPaint.measureText(text);
        Paint.FontMetrics fontMetrics = fontPaint.getFontMetrics();
        float x = getWidth() / 2 - textWidth / 2;
        float dy = -(fontMetrics.descent + fontMetrics.ascent) / 2;
        float y1 = getHeight() / 2 + dy;
        BitmapCanvas.drawText(text, x, y1, fontPaint);
        canvas.drawText("0%", radius, radius, fontPaint);
        canvas.drawBitmap(bitmap, 0, 0, RectPaint);
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());

    }

    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
    }

    @Override
    public void onClick(View v) {

    }
}
