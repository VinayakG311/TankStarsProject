package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.hud;
import com.mygdx.game.Screens.pauseScreen;
import com.mygdx.game.Sprites.Tank1;
import com.mygdx.game.Sprites.Tanktry;

public class trialMapScreen implements Screen {
    tankStars game;
    World world;
    Body body;


    Box2DDebugRenderer box2DDebugRenderer;
    private Viewport camPort;

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Tank1 tank1;
    Tank1 tank2;
    Tank tank;
    private Tanktry player;
    private Tanktry player2;
    private hud hud;
    private Texture tank_player1;
    private Texture tank_player2;
    private Stage stage;
    private Texture pausebutton;
    private ImageButton pauseButton;

    int turn=0;


    private static PolygonShape findPolygoninMap(PolygonMapObject polygonMapObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i];
        }

        polygon.set(worldVertices);
        return polygon;
    }


    private static ChainShape findPolylineinMap(PolylineMapObject polylineMapObject) {
        float[] vertices = polylineMapObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2];
            worldVertices[i].y = vertices[i * 2 + 1] ;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }


    public trialMapScreen(tankStars game,Texture tank,Texture rivtank){
        tank_player1=tank;
        tank_player2=rivtank;
        this.game=game;
        stage = new Stage(new ScreenViewport());
        pausebutton = new Texture("pause.jpg");

        hud = new hud(game.sprite);

        camera=new OrthographicCamera();
        camPort = new FitViewport(400,200,camera);

        tiledMap= new TmxMapLoader().load("GameMap.tmx");
        camera.position.set(600,350,0);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        world = new World(new Vector2(0,-100),true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        tank1=new Tank1(300,55,tank_player1,true,false);
        tank2=new Tank1(800,55,tank_player2,false,true);

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape2 = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();
        Body body;

        this.Wall(15,-170,15,1700);
        this.Wall(3625,-170,3625,1700);

        for(MapObject object : tiledMap.getLayers().get(2).getObjects()){

            Shape shape;

             if (object instanceof PolygonMapObject) {
                shape = findPolygoninMap((PolygonMapObject) object);
            } else if (object instanceof PolylineMapObject) {
                 shape = findPolylineinMap((PolylineMapObject) object);
             }

             else {
                continue;
            }
            bodyDef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bodyDef);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }

        player = new Tanktry(world,this,600,320,tank_player1);
        player2=new Tanktry(world,this,950,310,tank_player2);




    }

    public Texture getTank_player1(){
        return tank_player1;
    }


    @Override
    public void show() {
        Drawable drawable = new TextureRegionDrawable(pausebutton);
        Gdx.input.setInputProcessor(stage);
        pauseButton = new ImageButton(drawable);
        pauseButton.setSize(45,45);
        pauseButton.setPosition(45,550);

        pauseButton.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new pauseScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new pauseScreen(game));
                return true;
            }
        });


        stage.addActor(pauseButton);

    }


    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            if(turn==0){

                player.body.setLinearVelocity(0,0);
                turn = 1;
            }
            else{
                player2.body.setLinearVelocity(0,0);
                turn = 0;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && player.body.getLinearVelocity().x <=4){
            if(turn==0){
                player.body.applyLinearImpulse(new Vector2(100f,20f),player.body.getWorldCenter(),true);
            }
            else{
                player2.body.applyLinearImpulse(new Vector2(-100f,20f),player.body.getWorldCenter(),true);
            }
        }



        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && player.body.getLinearVelocity().x >=-4){
            if(turn==0) {
                player.body.applyLinearImpulse(new Vector2(-100f, 20f), player.body.getWorldCenter(), true);
            }
            else{
                player2.body.applyLinearImpulse(new Vector2(100f,20f),player.body.getWorldCenter(),true);

            }
        }




    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);
        player.update(dt);
        player2.update(dt);
//        if(turn==0) {
//            camera.position.x = player.body.getPosition().x;
//        }
//        else{
//            camera.position.x=player2.body.getPosition().x;
//        }
        camera.position.x = (player.body.getPosition().x+player2.body.getPosition().x)/2;

//        tank1.setPosition(new Vector3((float) (player.body.getPosition().x-tank1.getTank().getWidth()/2), (float) (player.body.getPosition().y-1.5*tank1.getTank().getHeight()),0));

        camera.update();

    }
    @Override
    public void render(float delta) {
        this.update(delta);


        Gdx.gl.glClearColor(38/255.0f, 45/255.0f, 107/255.0f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);


        renderer.render();
        game.sprite.setProjectionMatrix(camera.combined);
        game.sprite.begin();
        stage.act();

//        game.sprite.draw(tank1.getTank(),tank1.getPosition().x,tank1.getPosition().y);
        player.draw(game.sprite);

        player2.draw(game.sprite);



        game.sprite.end();

        stage.draw();
//        game.sprite.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.showHealth();
        renderer.setView(camera);
        box2DDebugRenderer.render(world,camera.combined);

    }
    public void Wall(int a,int b,int c,int d){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0,100);
        bodyDef.type=BodyDef.BodyType.StaticBody;
        body=world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density=2.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution=0;

        ChainShape shape = new ChainShape();

        shape.createChain(new Vector2[]{new Vector2(a,b),new Vector2(c,d)});
        shape.setRadius(0.1f);
        fixtureDef.shape=shape;
        body.createFixture(fixtureDef);

    }


    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        stage.getViewport().update(width,height);
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
