package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.Tank1;

public class MapScreen implements Screen {
    tankStars game;
    World world;
    Body body;
    Body b2body;
    Box2DDebugRenderer box2DDebugRenderer;
    private Viewport camPort;

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    TmxMapLoader loader;
    Tank1 tank1;
    Tank tank;
    public MapScreen(tankStars game){
        this.game=game;

        camera=new OrthographicCamera();

//        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//        camera.zoom+=0.5;
        camPort = new FitViewport(400,200,camera);
        //camera.update();
        tiledMap= new TmxMapLoader().load("newMap.tmx");
        camera.position.set(600,350,0);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        world=new World(new Vector2(0,-10),true);

        defground();
        box2DDebugRenderer=new Box2DDebugRenderer();
        tank1=new Tank1(100,55,new Texture("tank1.png"),true,false);
        deftank();


        // tank=new Tank(world,0,this,50,100,new Texture("tank1.png"));

        System.out.println(camPort.getWorldWidth()+" "+camPort.getWorldHeight());


    }
    @Override
    public void show() {

    }
    public void deftank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(50,110);

        bdef.type=BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.25f;
        fdef.restitution=0.75f;
        CircleShape shape = new CircleShape();
        shape.setRadius(10f);
        fdef.shape=shape;
        body.createFixture(fdef);

    }
    public void defground(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(0,100);
        bdef.type=BodyDef.BodyType.StaticBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.5f;
        fdef.restitution=0;

        ChainShape shape = new ChainShape();

        shape.createChain(new Vector2[]{new Vector2(-100,1.7f),new Vector2(700,-1.7f)});
        shape.setRadius(0.1f);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }

    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            //camera.position.x+=100*dt;
            body.applyLinearImpulse(new Vector2(10000f,0),body.getWorldCenter(),true);
           // tank1.movef();

         //   camera.update();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            camera.position.x-=100*dt;
            camera.update();
        }
    }

    public void update(float dt){
        handleInput(dt);
    //    tank1.update(dt);
        //   System.out.println(tank1.getPosition());
        tank1.setPosition(new Vector3((float) (body.getPosition().x-tank1.getTank().getWidth()/2), (float) (body.getPosition().y-1.3*tank1.getTank().getHeight()),0));

        camera.update();

    }
    @Override
    public void render(float delta) {
        this.update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.sprite.begin();
      //  game.sprite.draw(new Texture("background.png"),0,0);
        game.sprite.draw(tank1.getTank(),tank1.getPosition().x,tank1.getPosition().y);
       // game.sprite.draw(tank.getTank(),tank.getPosition().x,tank.getPosition().y,32,32);
        game.sprite.end();
//        if(Gdx.input.isTouched()){
//            camera.translate(Gdx.input.getDeltaX(),Gdx.input.getDeltaY());
//            camera.update();
//        }
        renderer.setView(camera);

        box2DDebugRenderer.render(world,camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
