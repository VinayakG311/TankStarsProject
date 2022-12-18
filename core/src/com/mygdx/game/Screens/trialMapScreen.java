package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ContactListen;
import com.mygdx.game.Scenes.hud;
import com.mygdx.game.Sprites.Tank1;
import com.mygdx.game.Sprites.Tanktry;
import com.mygdx.game.missiles;
import com.mygdx.game.states.saveload;
import com.mygdx.game.tankStars;

public class trialMapScreen implements Screen {
    private tankStars game;
    private World world;
    private Body body;
    private String t1,t2;
    private ShapeRenderer shapeRenderer;
    private Sprite sprite;
    private Sprite sprite2;
    private com.mygdx.game.missiles missile;
    private com.mygdx.game.missiles missile2;
    private int missilerenderplayer1;
    private int missilerenderplayer2;


    private Box2DDebugRenderer box2DDebugRenderer;
    private Viewport camPort;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;

    private Tank1 tank1;
    private Tank1 tank2;
    private com.mygdx.game.states.saveload saveload;
    private Texture explosion;

    private Tanktry player;
    private Tanktry player2;
    private hud hud;
    private Texture tank_player1;
    private Texture tank_player2;
    private Stage stage;
    private ContactListen contactListen;
    private Texture pausebutton;
    private Texture save;
    private ImageButton pauseButton;
    private ImageButton savegame;

    private int turn=0;
    private BitmapFont font;
    private Vector3 unproject;
    private Texture healthBar;

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


    public trialMapScreen(tankStars game,Texture tank,Texture rivtank,String t1,String t2){
        this.t1=t1;
        this.t2=t2;
        tank_player1=tank;
        tank_player2=rivtank;
        font=new BitmapFont();
        font.setColor(Color.WHITE);
        this.game=game;
        stage = new Stage(new ScreenViewport());
        pausebutton = new Texture("pause.jpg");
        save=new Texture("bullet.png");
        healthBar = new Texture("healthBar1.png");
        explosion = new Texture("explosion.png");

        unproject=new Vector3();

        sprite=new Sprite(new Texture("arrow.png"));
        sprite2=new Sprite(new Texture("arrow.png"));
        camera=new OrthographicCamera();
        camPort = new FitViewport(400,200,camera);
        missilerenderplayer1=0;
        missilerenderplayer2=0;

        tiledMap= new TmxMapLoader().load("GameMap.tmx");
        camera.position.set(600,350,0);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
        shapeRenderer=new ShapeRenderer();
        world = new World(new Vector2(0,-100),true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        saveload=new saveload();
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
            body.setUserData("ground");
        }

        player = new Tanktry(world,this,600,320,tank_player1,t1);
        player2=new Tanktry(world,this,950,310,tank_player2,t2);
        hud = new hud(game.sprite,player,player2);
        contactListen=new ContactListen();
        contactListen.getplayers(player,player2,hud);
        world.setContactListener(contactListen);
    }

    public Texture getTank_player1(){
        return tank_player1;
    }


    @Override
    public void show() {
        Drawable drawable = new TextureRegionDrawable(pausebutton);
        Drawable drawable1= new TextureRegionDrawable(save);
        Gdx.input.setInputProcessor(stage);
        pauseButton = new ImageButton(drawable);
        savegame=new ImageButton(drawable1);
        savegame.setSize(45,45);
        savegame.setPosition(45,500);
        pauseButton.setSize(45,45);
        pauseButton.setPosition(45,550);

        pauseButton.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new pauseScreen(game,t1,t2));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new pauseScreen(game,t1,t2));
                return true;
            }
        });
        savegame.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("hi");
                saveload.setstate(getplayer(),1);
                saveload.setstate(getplayer2(),2);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            //    saveload.setstate(trialMapScreen.this);
                System.out.println("hi");
                return true;
            }
        });

        stage.addActor(savegame);
        stage.addActor(pauseButton);

    }

    private Tanktry getplayer2() {
        return player2;
    }


    public void handleInput(float dt){

        if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            if(turn==0){

                player.body.setLinearVelocity(0,0);
//                turn = 1;
            }
            else{
                player2.body.setLinearVelocity(0,0);
//                turn = 0;
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
    public Tanktry getplayer(){
        return player;
    }

    public void update(float dt){
        handleInput(dt);
        world.step(1/60f,6,2);
        player.update(dt);
        player2.update(dt);
        if(missile!=null){

        missile.update(dt);}
        if(missile2!=null) {
            missile2.update(dt);
        }
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
      //  if(saveload.gethealth(1,1)!=null && saveload.gethealth(1,2)!=null){
         //   System.out.println(player.getT());
        //    System.out.println(saveload.getTexture(1,1));
        //    System.out.println("player1 hi"+saveload.getTexture(1,1)+"hi "+saveload.gethealth(1,1)+" "+saveload.getposX(1,1)+" "+saveload.getposY(1,1)+" "+saveload.getAngle(1,1));
            //     System.out.println("player2 hi"+saveload.getTexture(1,2)+"hi "+saveload.gethealth(1,2)+" "+saveload.getposX(1,2)+" "+saveload.getposY(1,2)+" "+saveload.getAngle(1,2));
    //    }
        this.update(delta);


        Gdx.gl.glClearColor(38/255.0f, 45/255.0f, 107/255.0f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        unproject.set(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(unproject);
       // System.out.println(unproject);
       // System.out.println(Gdx.input.getX()+" "+Gdx.input.getY());

        renderer.render();
        game.sprite.setProjectionMatrix(camera.combined);
        game.sprite.begin();
      //  game.sprite.draw(healthBar,850,560,340,70);
      //  game.sprite.draw(healthBar,370,560,340,70);
        stage.act();
        if(turn==0) {
            //  System.out.println(unproject.y+" "+player.body.getPosition().y+" "+unproject.x+" "+player.body.getPosition().x);
            if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)){
                player.setAngle(player.getAngle()+1);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN)){
                player.setAngle(player.getAngle()-1);
            }
            font.draw(game.sprite, String.valueOf((int)(player.getAngle())), player.getX(), player.getY() + 50);
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                missile=new missiles(new Texture("bullet.png"),world,100,100, (int) player.getX()+40, (int) player.getY()+60);
                Vector2 x= new Vector2(100,500);
                x.rotateDeg((float) (player.getAngle()-90));
                missile.body.setLinearVelocity(x);
                missilerenderplayer1 = 1;
                turn = 1;

            }
        }
        else {
            //  System.out.println(unproject.y+" "+player.body.getPosition().y+" "+unproject.x+" "+player.body.getPosition().x);
            if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP)){
                player2.setAngle(player2.getAngle()+1);
            }
            if(Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN)){
                player2.setAngle(player2.getAngle()-1);
            }
            font.draw(game.sprite, String.valueOf((int)(player2.getAngle())), player2.getX(), player2.getY() + 50);
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                missile2=new missiles(new Texture("bullet.png"),world,100,100, (int) (player2.getX()-30), (int) (player2.getY()+50));

                Vector2 x= new Vector2(-100,500);
                x.rotateDeg((float) (90-player2.getAngle()));
                missile2.body.setLinearVelocity(x);
                missilerenderplayer2 = 1;

                turn = 0;

            }
        }

//        game.sprite.draw(tank1.getTank(),tank1.getPosition().x,tank1.getPosition().y);
        player.draw(game.sprite);
        player2.draw(game.sprite);
        if(player.fire==1){
            game.sprite.draw(explosion,player.getX(),player.getY(),70,100);
            player.toggle();
        }
        if(player2.fire==1){
            game.sprite.draw(explosion,player2.getX(),player2.getY(),70,100);
            player2.toggle();
        }
        if(missilerenderplayer1==1) {
            missile.draw(game.sprite);
        }
//
//            if(Math.abs(missile.getX()-player2.getX())<=75){
//
//                game.sprite.draw(explosion,player2.getX(),player2.getY(),70,100);
//            }
//
//        }
        if(missilerenderplayer2==1) {
            missile2.draw(game.sprite);
        }
//            if(Math.abs(missile2.getX()-player.getX())<=75){
//
//                game.sprite.draw(explosion,player.getX(),player.getY(),70,100);
//
//            }
//
//        }

        if(turn==0) {
            sprite.setRotation((float) (player.getAngle() - 90));
            sprite.setSize(50, 50);
            sprite.setPosition(player.getX(), player.getY());
            sprite.draw(game.sprite);
        }
        else{

            sprite2.setRotation((float) (90-player2.getAngle()));
            sprite2.setSize(50, 50);
            sprite2.setPosition(player2.getX(), player2.getY());
            sprite2.draw(game.sprite);

        }
     //   game.sprite.draw(new Texture("arrow.png"),player.getX(),player.getY(),50,50);





        game.sprite.end();

        shapeRenderer.end();
        stage.draw();
        hud.stage.draw();
        game.sprite.setProjectionMatrix(hud.stage.getCamera().combined);
        if(player.getHealth()<=0 || player2.getHealth()<=0){
            if(player.getHealth()<=0){
                hud.showHealth(0, (float) player2.getHealth());

                this.game.setScreen(new GameOver(this.game,1,t1,t2));

            }
            else{
                hud.showHealth((float) player.getHealth(), 0);
                System.out.println("hi");
                this.game.setScreen(new GameOver(this.game,0,t1,t2));
            }

        }
        else {
            hud.showHealth((float) player.getHealth(), (float) player2.getHealth());
        }
        renderer.setView(camera);
       for(Contact contact: world.getContactList()){
           Body a=contact.getFixtureA().getBody();
           Body b=contact.getFixtureB().getBody();
           if(a.getUserData() instanceof missiles && b.getUserData() instanceof missiles){
                 System.out.println("hi");
           }
       }
        box2DDebugRenderer.render(world,camera.combined);

      //  System.out.println(world.);


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
