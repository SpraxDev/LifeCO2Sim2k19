package de.sprax2013.life_co2_sim_2k19;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import de.sprax2013.life_co2_sim_2k19.scenes.SceneManager;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private int lastX, lastY;
    private boolean SHOW_LAST_COORDS = true;

    public GamePanel(Context ctx) {
        super(ctx);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        SceneManager.init();

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

//        Constants.INIT_TIME = System.currentTimeMillis();

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

                retry = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (SHOW_LAST_COORDS) {
            lastX = Math.round(e.getX());
            lastY = Math.round(e.getY());
        }

        return SceneManager.onMotionEvent(e);
    }

    public void update() {
        SceneManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas != null) {
            super.draw(canvas);

            SceneManager.draw(canvas);

            if (SHOW_LAST_COORDS) {
                Paint paint = new Paint();
                paint.setColor(Color.YELLOW);
                paint.setTextSize((float) getHeight() / 14);

                canvas.drawText("(" + Math.round(lastX) + "|" + Math.round(lastY) + ")", 0, paint.getTextSize() * 2, paint);
            }

            if (MainActivity.isBeta()) {
                Paint paint = new Paint();
                paint.setColor(Color.RED);
                paint.setTextSize((float) getHeight() / 14);

                String txt = "v" + MainActivity.getVersionName();

                canvas.drawText(txt, getWidth() - paint.measureText(txt), paint.getTextSize(), paint);
            }
        }
    }
}