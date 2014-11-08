package com.paranoidalien.com.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import static com.badlogic.gdx.Input.Keys.SPACE;

/**
 * Created by wilkdavi on 11/7/2014.
 */
public class MyInputProcessor implements InputProcessor {

    private OrthographicCamera cam;
    Vector3 screenCoords;

    public MyInputProcessor(OrthographicCamera cam){
        this.cam = cam;
        screenCoords = new Vector3();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE){
            System.out.println("Space...");
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenCoords.set(screenX, screenY, 0);

        // Convert screen coordinate system to world coordinate system
        cam.unproject(screenCoords);
        System.out.println("Real screen coords: " + screenX + ", " + screenY);
        System.out.println("World Coords:" + screenCoords + " pointer:" + pointer + " button:" + button);

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        //cam.zoom += amount * 0.2f;
        cam.rotate(amount);
        return false;
    }
}
