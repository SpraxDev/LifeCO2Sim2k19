package de.sprax2013.life_co2_sim_2k19.scenes.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import de.sprax2013.life_co2_sim_2k19.MainActivity;
import de.sprax2013.life_co2_sim_2k19.R;
import de.sprax2013.life_co2_sim_2k19.scenes.Scene;

public class GameMenuScene implements Scene {
    private Bitmap bg, bicycle, car, publicTransport;
    private Rect txtRect, bicycleRect, carRect, publicTransportRect;
    private int lastDownX, lastDownY;

    private Paint paint;

    public GameMenuScene() {
        bg = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bg);

        bicycle = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bike_1);
        car = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.car_blue);
        publicTransport = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.train);
    }

    @Override
    public void onMotionEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            lastDownX = (int) e.getX();
            lastDownY = (int) e.getY();
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) e.getX(), y = (int) e.getY();

            if (bicycleRect != null && bicycleRect.contains(lastDownX, lastDownY) && bicycleRect.contains(x, y)) {
                System.out.println("Bicycle");
//                SceneManager.setActive(SceneType.GAME_MENU);
            } else if (carRect != null && carRect.contains(lastDownX, lastDownY) && carRect.contains(x, y)) {
                System.out.println("Car");
//                SceneManager.setActive(SceneType.SCORE_BOARD);
            } else if (publicTransportRect != null && publicTransportRect.contains(lastDownX, lastDownY) && publicTransportRect.contains(x, y)) {
                System.out.println("Public Transport");
//                SceneManager.setActive(SceneType.CREDITS);
            }
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bg, null, new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        Point canvasCenter = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);

        int rectH = canvas.getHeight() / 6;

        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize((float) canvas.getHeight() / 14);
        }

        if (txtRect == null || bicycleRect == null || carRect == null || publicTransportRect == null) {
            txtRect = new Rect(canvasCenter.x, canvasCenter.y - rectH, canvasCenter.x, canvasCenter.y - rectH);

            bicycleRect = new Rect(canvasCenter.x - (canvas.getWidth() / 9), canvasCenter.y - (rectH / 5), canvasCenter.x + (canvas.getWidth() / 9), canvasCenter.y + (rectH / 2));
            carRect = new Rect(canvasCenter.x - (canvas.getWidth() / 10), bicycleRect.top + rectH, canvasCenter.x + (canvas.getWidth() / 10), bicycleRect.bottom + rectH);
            publicTransportRect = new Rect(canvasCenter.x - (canvas.getWidth() / 10), carRect.top + rectH, canvasCenter.x + (canvas.getWidth() / 10), carRect.bottom + rectH);
        }

        canvas.drawText("How do you want to get to school?", txtRect.centerX(), txtRect.centerY(), paint);

        canvas.drawBitmap(bicycle, null, bicycleRect, null);
        canvas.drawBitmap(car, null, carRect, null);
        canvas.drawBitmap(publicTransport, null, publicTransportRect, null);

        /* DEBUG */

//        Paint p1 = new Paint();
//        p1.setColor(Color.RED);

//        Paint p2 = new Paint();
//        p2.setColor(Color.YELLOW);

//        Paint p3 = new Paint();
//        p3.setColor(Color.CYAN);


//        canvas.drawRect(bicycleRect, p1);
//        canvas.drawRect(carRect, p2);
//        canvas.drawRect(publicTransportRect, p3);
    }

    @Override
    public void destroyScene() {
    }
}