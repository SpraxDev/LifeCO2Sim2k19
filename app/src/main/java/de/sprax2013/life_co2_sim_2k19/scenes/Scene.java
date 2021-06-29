package de.sprax2013.life_co2_sim_2k19.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface Scene {
    void onMotionEvent(MotionEvent e);

    void update();

    void draw(Canvas canvas);

    void destroyScene();
}
