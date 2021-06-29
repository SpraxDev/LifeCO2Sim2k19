package de.sprax2013.life_co2_sim_2k19.scenes;

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

public class CreditsScene implements Scene {
    private Bitmap bg, backToMenu;
    private Rect backToMenuRect;

    private int lastDownX, lastDownY;

    public CreditsScene() {
        bg = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bg_menu);
        backToMenu = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.txt_backtomenu);
    }

    @Override
    public void onMotionEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            lastDownX = (int) e.getX();
            lastDownY = (int) e.getY();
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) e.getX(), y = (int) e.getY();

            if (backToMenuRect != null && backToMenuRect.contains(lastDownX, lastDownY) && backToMenuRect.contains(x, y)) {
                SceneManager.setActive(SceneType.MAIN_MENU);
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

        int rectW = canvas.getWidth() / 4;
        int rectH = canvas.getHeight() / 6;

        if (backToMenuRect == null) {
            backToMenuRect = new Rect(canvasCenter.x - (rectW / 2), canvasCenter.y - (rectH / 2),
                    canvasCenter.x + (rectW / 2), canvasCenter.y + (rectH / 2));
        }

        canvas.drawBitmap(backToMenu, null, backToMenuRect, null);

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.CYAN);
        paint.setFakeBoldText(true);
        paint.setTextSize((float) canvas.getHeight() / 14);

        canvas.drawText("Sprax2013", canvasCenter.x, canvasCenter.y + rectH, paint);
        canvas.drawText("NudelErde", canvasCenter.x, canvasCenter.y + (rectH * 1.5f), paint);
        canvas.drawText("RobertFunk72", canvasCenter.x, canvasCenter.y + (rectH * 2), paint);
        canvas.drawText("Arek", canvasCenter.x, canvasCenter.y + (rectH * 2.5f), paint);
        canvas.drawText("Linus", canvasCenter.x, canvasCenter.y + (rectH * 3), paint);
    }

    @Override
    public void destroyScene() {
    }
}