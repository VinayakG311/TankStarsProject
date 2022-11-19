package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.hud;
import com.mygdx.game.Tank;
import com.mygdx.game.tankStars;


public class PlayScreen implements Screen {
    private tankStars game;
    private final Texture backGround;
    private final Texture ground;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private ShapeRenderer shapeRenderer;
    private hud hud;
    private BitmapFont bf_loadProgress;
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Tank player ;
    public Body b2body;
    //private World world;


    public PlayScreen(tankStars game){
        this.game=game;
        gamecam=new OrthographicCamera();
        gamePort=new ExtendViewport(850,480,gamecam);
        backGround = new Texture("background.jpg");
        ground=new Texture("map.png");

        hud = new hud(game.sprite);
        bf_loadProgress = new BitmapFont();
        bf_loadProgress.getData().setScale(2,1);
        shapeRenderer = new ShapeRenderer();

       // player= new Tank(world);

//        maploader=new TmxMapLoader();
//        map=maploader.load("map.jpg");
//        renderer=new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(400,240,0);




    }
    public void handleinput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && player.character.getLinearVelocity().x <=2){
            player.character.applyLinearImpulse(new Vector2(0.1f,0),player.character.getWorldCenter(),true);

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && player.character.getLinearVelocity().x >=-2){
            player.character.applyLinearImpulse(new Vector2(-0.1f,0),player.character.getWorldCenter(),true);

        }



    }
    public void update(float dt){
        handleinput(dt);

        world.step(1/60f,6,2);
       // player.character.applyForceToCenter(player.movement,true);
        gamecam.position.x = player.character.getPosition().x;
        //hud.stage.getCamera().position.x = player.character.getPosition().x;
        gamecam.update();
      //  renderer.setView(gamecam);

    }
    @Override
    public void show() {
        world=new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();
        player = new Tank(world);

        if(Gdx.input.getInputProcessor().keyDown(Input.Keys.D)){
            player.movement.x=500;
        }
//        player.def();
//        BodyDef bdef = new BodyDef();
//        bdef.position.set(0,1);
//        bdef.type=BodyDef.BodyType.DynamicBody;
//        b2body=world.createBody(bdef);
//        FixtureDef fdef = new FixtureDef();
//        fdef.density=2.5f;
//        fdef.friction = 0.25f;
//        fdef.restitution=0.75f;
//        System.out.println(Gdx.graphics.getWidth());
//
//        CircleShape shape = new CircleShape();
//        shape.setRadius(10f);
//        fdef.shape=shape;
//        b2body.createFixture(fdef);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       // renderer.render();
       // System.out.println(gamecam.position);
        System.out.println(gamecam.position);
        //g
        game.sprite.begin();
        game.sprite.draw(backGround,0,0,850,500);
        game.sprite.draw(ground,0,0,850,100);
        game.sprite.end();
        hud.showHealth();
        b2dr.render(player.world,player.gamecam.combined);
       // game.sprite.setProjectionMatrix(gamecam.combined);
        game.sprite.setProjectionMatrix(hud.stage.getCamera().combined);


    }
    public void showHealth(){
       
        float health = 175;
        shapeRenderer.setProjectionMatrix(gamecam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(-260, 225, health, 30);
        shapeRenderer.rect(150, 225, health, 30);
        shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
