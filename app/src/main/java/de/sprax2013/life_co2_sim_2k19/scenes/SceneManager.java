package de.sprax2013.life_co2_sim_2k19.scenes;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.HashMap;

import de.sprax2013.life_co2_sim_2k19.scenes.game.BikeScene;
import de.sprax2013.life_co2_sim_2k19.scenes.game.GameMenuScene;

public class SceneManager {
    private static HashMap<SceneType, Scene> scenes = new HashMap<>();

    private static Scene currScene;

    public static void init() {
        for (Scene scene : scenes.values()) {
            scene.destroyScene();
        }
        scenes.clear();
        currScene = null;

//        setActive(SceneType.MAIN_MENU);

        // DEBUG
        setActive(SceneType.GAME_BIKE);
    }

    public static void setActive(SceneType sceneType) {
        if (!scenes.containsKey(sceneType)) {
            Scene scene = null;

            switch (sceneType) {
                case MAIN_MENU:
                    scene = new MainMenuScene();
                    break;
                case CREDITS:
                    scene = new CreditsScene();
                    break;
                case SCORE_BOARD:
                    scene = new ScoreBoardScene();
                    break;

                case GAME_MENU:
                    scene = new GameMenuScene();
                    break;
                case GAME_BIKE:
                    scene = new BikeScene();
                default:
                    break;
            }

            if (scene != null) {
                scenes.put(sceneType, scene);
            }
        }

        currScene = scenes.get(sceneType);
    }

    public static boolean onMotionEvent(MotionEvent e) {
        if (currScene != null) {
            currScene.onMotionEvent(e);
        }

        return true;
    }

    public static void update() {
        if (currScene != null) {
            currScene.update();
        }
    }

    public static void draw(Canvas canvas) {
        if (currScene != null) {
            currScene.draw(canvas);
        }
    }
}