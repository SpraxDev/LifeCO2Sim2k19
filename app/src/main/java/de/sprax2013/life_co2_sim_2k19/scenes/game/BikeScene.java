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

public class BikeScene implements Scene {
    private Bitmap bg, bicycle_1, bicycle_2;
    private Rect txtRect, bicycleRect, carRect;
    private int lastDownX, lastDownY;

    private Paint paint;

    public BikeScene() {
        bg = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bg_bike);

        bicycle_1 = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bike_1);
        bicycle_2 = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bike_2);
    }

    @Override
    public void onMotionEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            lastDownX = (int) e.getX();
            lastDownY = (int) e.getY();
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bg, null, new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), null);

        Point canvasCenter = new Point(canvas.getWidth() / 2, canvas.getHeight() / 2);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize((float) canvas.getHeight() / 14);
        }

        if (txtRect == null || bicycleRect == null || carRect == null) {
            txtRect = new Rect(canvasCenter.x - (canvas.getWidth() / 9), canvasCenter.y - (canvas.getHeight() / 3), canvasCenter.x + (canvas.getWidth() / 9), canvas.getHeight() / 4);

            bicycleRect = new Rect(canvas.getWidth() / 15, canvas.getHeight() - canvas.getHeight() / 3, canvasCenter.x - canvasCenter.x / 2, canvas.getHeight() - canvas.getHeight() / 6);
            carRect = new Rect(canvas.getWidth() / 15, canvas.getHeight() - canvasCenter.y, canvasCenter.x - canvasCenter.x / 2, canvas.getHeight() - canvas.getHeight() / 4);
        }

        canvas.drawText("Tap to jump", txtRect.centerX(), txtRect.centerY(), paint);

        canvas.drawBitmap(bicycle_1, null, bicycleRect, null);

//        Paint p1 = new Paint();
//        p1.setColor(Color.RED);
//        canvas.drawRect(bicycleRect, p1);

//        canvas.drawBitmap(bicycle, null, bicycleRect, null);
//        canvas.drawBitmap(car, null, carRect, null);
//        canvas.drawBitmap(publicTransport, null, publicTransportRect, null);
    }

    @Override
    public void destroyScene() {
    }
}