package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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
//        world=new World(new Vector2(0,-10),true);
//        deftank();
//        defground();
//        box2DDebugRenderer=new Box2DDebugRenderer();

        System.out.println(camPort.getWorldWidth()+" "+camPort.getWorldHeight());


    }
    @Override
    public void show() {

    }
    public void deftank(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(50,100);

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
        bdef.position.set(0,0);
        bdef.type=BodyDef.BodyType.StaticBody;
        b2body=world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        fdef.density=2.5f;
        fdef.friction = 0.5f;
        fdef.restitution=0;

        ChainShape shape = new ChainShape();

        shape.createChain(new Vector2[]{new Vector2(-500,-1.7f),new Vector2(500,-1.7f)});
        shape.setRadius(0.1f);
        fdef.shape=shape;
        b2body.createFixture(fdef);

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            camera.position.x+=100*dt;
            camera.update();
        }
    }

    public void update(float dt){
        handleInput(dt);
        camera.update();

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        game.sprite.begin();
//        game.sprite.draw(new Texture("background.png"),0,0);
//        game.sprite.end();
//        if(Gdx.input.isTouched()){
//            camera.translate(Gdx.input.getDeltaX(),Gdx.input.getDeltaY());
//            camera.update();
//        }
        this.update(delta);
        renderer.setView(camera);

        renderer.render();
//        box2DDebugRenderer.render(world,camera.combined);

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
