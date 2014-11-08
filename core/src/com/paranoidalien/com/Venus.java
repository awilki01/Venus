package com.paranoidalien.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.paranoidalien.com.Screens.GameScreen;
import com.paranoidalien.com.Screens.MainMenuScreen;
import com.paranoidalien.com.input.MyInputProcessor;

public class Venus extends Game {

    public SpriteBatch batch;
	public BitmapFont font;


	
	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));

	}

	@Override
	public void render () {
        super.render();
	}



    @Override
    public void resize(int width, int height) {
        //cam.viewportWidth = 30f;
        //cam.viewportHeight = 30f * height / width;
        //cam.update();
    }

    @Override
    public void dispose(){
        batch.dispose();

    }

}

