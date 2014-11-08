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
 * Created by Adam on 08 November 2014 at 9:55 AM.
 */
public class GameScreen implements Screen{
    final Venus game;
    private SpriteBatch batch;
    private Texture img;
    //private OrthographicCamera cam;
    private Sprite mapSprite;
    private float rotationSpeed;

    OrthographicCamera cam;

    public GameScreen (final Venus game){
        this.game = game;

        rotationSpeed = 0.5f;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(30, 30 * (h / w));
        //cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.position.set(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f, 0);
        cam.update();

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new MyInputProcessor(cam));

    }

    @Override
    public void render(float delta) {
        //handleInput();
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapSprite.draw(batch);
        batch.end();

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
