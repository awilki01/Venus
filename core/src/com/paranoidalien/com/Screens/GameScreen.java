package com.paranoidalien.com.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
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
    private Sprite smileySprite;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private CircleShape circle;
    private PolygonShape groundBox;
    private Body body;
    private Vector2 bodyPos = new Vector2();
    private Vector2 bodyImpulse;


    OrthographicCamera cam;

    public GameScreen (final Venus game){
        this.game = game;

        // Set up camera
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(30, 30 * (h / w));
        cam.position.set(Constants.WORLD_WIDTH / 2f, Constants.WORLD_HEIGHT / 2f, 0);
        cam.update();

        // Set up image texture and sprite ----------------------------------
        img = new Texture(Gdx.files.internal("smiley.png"));
        smileySprite = new Sprite(img,0, 0, 128, 128);
        smileySprite.setSize(1, 1);
        smileySprite.setOriginCenter();  // Do this last when setting up the sprite


        batch = new SpriteBatch();

        // Add an input listener ---------------------------------------------
        Gdx.input.setInputProcessor(new MyInputProcessor(cam));

        // Set up box2d ------------------------------------------------------
        world = new World(new Vector2(0, -9.81f), true);
        debugRenderer = new Box2DDebugRenderer();

        // Create the box2d ball ---------------------------------------------
        // Create body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(50, 50);

        // Create body in the world using body definition
        body = world.createBody(bodyDef);

        // Create a circle shape
        CircleShape circle = new CircleShape();
        circle.setRadius(0.5f);

        // Create a fixture definition to apply the shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 27f;
        fixtureDef.friction = 0.8f;
        fixtureDef.restitution = 0.4f;

        // Create fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

        // Create box2d floor ---------------------------------------------------
        // Create body definition
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(Constants.WORLD_WIDTH / 2, 10));

        // Create a body from the definition and add it to the world
        Body groundBody = world.createBody(groundBodyDef);

        // Create a polygon shape
        PolygonShape groundBox = new PolygonShape();

        // Set the polygon shape as wide as the world and 1 meter high
        // Note: setAsBox takes half-width and half-height as arguments
        groundBox.setAsBox(Constants.WORLD_WIDTH / 2, 0.5f);

        // Create a fixture from our polygon shape and add it to the ground body
        groundBody.createFixture(groundBox, 0.0f);

        // This is the force used to jump
        bodyImpulse = new Vector2(0, 200f);



        game.font.setScale(0.2f);

    }

    @Override
    public void render(float delta) {

        //cam.position.set(body.getPosition().x, body.getPosition().y, 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        bodyPos = body.getWorldCenter();
        //body.setUserData(smileySprite);

        smileySprite.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);
        smileySprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());

        batch.begin();
        smileySprite.draw(batch);
        game.font.draw(batch, "Scale Font", 30, 30);
        batch.end();
        //System.out.println(batch.renderCalls);


        // Move ball
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            body.applyTorque(300, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            body.applyTorque(-300, true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            body.applyLinearImpulse(bodyImpulse, bodyPos, true);


        }

        // Set max and min angular velocity
        body.setAngularVelocity(MathUtils.clamp(body.getAngularVelocity(), -15, 15));


        // Render box2D
        debugRenderer.render(world, cam.combined);

        // Step the box2d engine
        world.step(1/60f, 6, 2);
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
        circle.dispose();
        groundBox.dispose();
        batch.dispose();
    }
}
