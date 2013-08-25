package com.example.gamedummy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sayazu on 22/08/13.
 */

public class MyView extends View {
    private Paint paint;
    private int circleX;
    private int circleY;
    private float radius;

    public MyView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        circleX = 100;
        circleY = 100;
        radius = 25;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(this.circleX, this.circleY, this.radius, this.paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                this.circleX = (int) event.getX();
                this.circleY = (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
