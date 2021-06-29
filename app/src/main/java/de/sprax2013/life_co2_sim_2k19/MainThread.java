package de.sprax2013.life_co2_sim_2k19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    public static boolean SHOW_FPS = true;

    public static Canvas canvas;
    private final SurfaceHolder surfaceHolder;
    private final GamePanel gamePanel;
    private double averageFPS;
    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long waitTime;

        int frameCount = 0;

        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);

                    if (SHOW_FPS) {
                        Paint paint = new Paint();
                        paint.setColor(Color.YELLOW);
                        paint.setTextSize((float) canvas.getHeight() / 14);

                        canvas.drawText(Math.round(averageFPS) + " FPS", 0, paint.getTextSize(), paint);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            long timeMillis = (System.nanoTime() - startTime) / 1_000_000;
            waitTime = targetTime - timeMillis;

            try {
                if (waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            totalTime += System.nanoTime() - startTime;
            frameCount++;

            if (frameCount == MAX_FPS) {
                averageFPS = 1000 / (((double) totalTime / frameCount) / 1_000_000) + 1;
                frameCount = 0;
                totalTime = 0;
            }
        }
    }
}