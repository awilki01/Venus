package com.paranoidalien.com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.paranoidalien.com.input.MyInputProcessor;

public class Venus extends ApplicationAdapter {
    public static final int WORLD_WIDTH = 100;
    public static final int WORLD_HEIGHT = 100;
    private SpriteBatch batch;
	private Texture img;
    private OrthographicCamera cam;
    private Sprite mapSprite;
    private float rotationSpeed;


	
	@Override
	public void create () {
        rotationSpeed = 0.5f;

        mapSprite = new Sprite(new Texture(Gdx.files.internal("sc_map.png")));
        mapSprite.setPosition(0, 0);
        mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);

		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(30, 30 * (h / w));
        //cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);
        cam.update();

        batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new MyInputProcessor());

	}

	@Override
	public void render () {
        handleInput();
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        mapSprite.draw(batch);
        batch.end();
	}

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            cam.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            cam.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            cam.translate(-0.2f, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            cam.translate(0.2f, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            cam.translate(0, -0.2f, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            cam.translate(0, 0.2f, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            cam.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            cam.rotate(rotationSpeed, 0, 0, 1);
        }

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);
        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);

    }

    @Override
    public void resize(int width, int height) {
        //cam.viewportWidth = 30f;
        //cam.viewportHeight = 30f * height / width;
        //cam.update();
    }

    @Override
    public void dispose(){
        mapSprite.getTexture().dispose();
        batch.dispose();

    }

}

