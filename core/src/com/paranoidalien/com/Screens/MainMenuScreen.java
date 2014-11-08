package com.paranoidalien.com.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.paranoidalien.com.Utils.Constants;
import com.paranoidalien.com.Venus;
import com.paranoidalien.com.input.MyInputProcessor;

/**
 * Project: Venus
 * Created by Adam on 08 November 2014 at 9:39 AM.
 */
public class MainMenuScreen implements Screen {

    final Venus game;
    private SpriteBatch batch;
    private Texture img;
    //private OrthographicCamera cam;
    private Sprite mapSprite;
    private float rotationSpeed;

    OrthographicCamera cam;

    public MainMenuScreen(final Venus game){
        this.game = game;

        rotationSpeed = 0.5f;

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        //cam = new OrthographicCamera();
        // cam.setToOrtho(false, 800, 480);

        cam = new OrthographicCamera(w, h);
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cam.update();
        game.batch.setProjectionMatrix(cam.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
