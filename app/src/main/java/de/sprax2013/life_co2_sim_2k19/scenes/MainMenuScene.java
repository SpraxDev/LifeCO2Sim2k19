package de.sprax2013.life_co2_sim_2k19.scenes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import de.sprax2013.life_co2_sim_2k19.MainActivity;
import de.sprax2013.life_co2_sim_2k19.R;

public class MainMenuScene implements Scene {
    private Bitmap bg, start, scoreboard, credits;
    private Rect startRect, sbRect, creditsRect;

    private int lastDownX, lastDownY;

    public MainMenuScene() {
        bg = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.bg_menu);

        start = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.txt_start);
        scoreboard = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.txt_scoreboard);
        credits = BitmapFactory.decodeResource(MainActivity.getContext().getResources(), R.drawable.txt_credits);
    }

    @Override
    public void onMotionEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            lastDownX = (int) e.getX();
            lastDownY = (int) e.getY();
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) e.getX(), y = (int) e.getY();

            if (startRect != null && startRect.contains(lastDownX, lastDownY) && startRect.contains(x, y)) {
                SceneManager.setActive(SceneType.GAME_MENU);
            } else if (sbRect != null && sbRect.contains(lastDownX, lastDownY) && sbRect.contains(x, y)) {
                SceneManager.setActive(SceneType.SCORE_BOARD);
            } else if (creditsRect != null && creditsRect.contains(lastDownX, lastDownY) && creditsRect.contains(x, y)) {
                SceneManager.setActive(SceneType.CREDITS);
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

        if (startRect == null || sbRect == null || creditsRect == null) {
            startRect = new Rect(canvasCenter.x - (rectW / 2), canvasCenter.y - (rectH / 2),
                    canvasCenter.x + (rectW / 2), canvasCenter.y + (rectH / 2));
            sbRect = new Rect(canvasCenter.x - (rectW), canvasCenter.y + (rectH / 2),
                    canvasCenter.x + (rectW), canvasCenter.y + ((rectH / 2) * 2) * 2);
            creditsRect = new Rect(canvasCenter.x - (rectW / 2), canvasCenter.y + (rectH / 2) * 4,
                    canvasCenter.x + (rectW / 2), canvasCenter.y + ((rectH / 2) * 2) * 3);
        }

        canvas.drawBitmap(start, null, startRect, null);
        canvas.drawBitmap(scoreboard, null, sbRect, null);
        canvas.drawBitmap(credits, null, creditsRect, null);
    }

    @Override
    public void destroyScene() {
    }
}