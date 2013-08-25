package com.example.gamedummy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sayazu on 22/08/13.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private MyThread myThread;

    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true); // make the GamePanel focusable so it can handle events

        myThread = new MyThread(getHolder(), this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.myThread.setRunning(true);
        this.myThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        this.myThread.setRunning(false);
        try {
            while (retry) {
                this.myThread.join();
                retry = false;
            }
        } catch (InterruptedException e) {
            // try again shutting down the thread
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*    if (this.myThread.isRunning()) {
            this.myThread.setRunning(false);
        } else {
            this.myThread.setRunning(true);
            this.myThread.start();
        }
*/
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
