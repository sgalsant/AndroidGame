package com.example.gamedummy;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by sayazu on 22/08/13.
 */
public class MyThread extends  Thread {
    private static final String TAG = MyThread.class.getSimpleName();


    private boolean running = false;
    private SurfaceHolder holder;
    private MySurfaceView view;

    private Paint paint;
    private int circleX;
    private int circleY;
    private float radius;
    private int speedX = 2;
    private int speedY = 2;

    public MyThread(SurfaceHolder holder, MySurfaceView view) {
        super();
        this.holder = holder;
        this.view = view;

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(Color.RED);

        this.circleX = 30;
        this.circleY = 30;
        this.radius = 10;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }


    private void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        this.paint.setColor(Color.RED);
        canvas.drawCircle(this.circleX, this.circleY, this.radius, this.paint);
    }

    private void update() {
        if (this.circleX >= this.view.getWidth()) {
            this.speedX = -Math.abs(this.speedX);
        } else if (this.circleX < 0) {
            this.speedX = Math.abs(this.speedX);
        }

        if (this.circleY >= this.view.getHeight()) {
            this.speedY = -Math.abs(this.speedY);
        } else if (this.circleY < 0) {
            this.speedY = Math.abs(this.speedY);
        }

        this.circleX += this.speedX;
        this.circleY += this.speedY;
    }

    //region Basico
    @Override
    public void run() {
        Log.d(TAG, "iniciado...");
        while (this.running) {
            Canvas canvas = null;
            try {
                canvas = this.holder.lockCanvas(null);
                synchronized (this.holder) {
                    update();

                    draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    this.holder.unlockCanvasAndPost(canvas);
                }
            }
        }

        Log.d(TAG, "finalizado");
    }
    //endregion



    //region FPS
    /*
    // FPS: http://obviam.net/index.php/the-android-game-loop
    // desired fps
    private final static int 	MAX_FPS = 50;
    // maximum number of frames to be skipped
    private final static int	MAX_FRAME_SKIPS = 5;
    // the frame period
    private final static int	FRAME_PERIOD = 1000 / MAX_FPS;


    @Override
    public void run() {
        Log.d(TAG, "Starting game loop");

        long beginTime;		// the time when the cycle begun
        long timeDiff;		// the time it took for the cycle to execute
        int sleepTime;		// ms to sleep (<0 if we're behind)
        int framesSkipped;	// number of frames being skipped

        sleepTime = 0;

        while (running) {
            Canvas canvas = null;
            // try locking the canvas for exclusive pixel editing
            // in the surface
            try {
                canvas = this.holder.lockCanvas();
                synchronized (this.holder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;	// resetting the frames skipped
                    // update game state
                    update();
                    // render state to the screen
                    // draws the canvas on the panel
                    draw(canvas);
                    // calculate how long did the cycle take
                    timeDiff = System.currentTimeMillis() - beginTime;
                    // calculate sleep time
                    sleepTime = (int)(FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {
                        // if sleepTime > 0 we're OK
                        try {
                            // send the thread to sleep for a short period
                            // very useful for battery saving
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {}
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                        // we need to catch up
                        // update without rendering
                        update();
                        // add frame period to check if in next frame
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }
                }
            } finally {
                // in case of an exception the surface is not left in
                // an inconsistent state
                if (canvas != null) {
                    this.holder.unlockCanvasAndPost(canvas);
                }
            }	// end finally
        }
    }
    */
    //endregion

}
