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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.hud;
import com.mygdx.game.Sprites.Tank1;
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
    private Sprite sprite;
    //private World world;
    private TextureAtlas atlas,pauseAtlas;
    private Texture pause;
    private Skin buttonSkin;
    private ImageButton pauseButton;
    private Stage stage;
    private Texture pausebutton;
    private Tank1 tank1;
    private Tank1 tank2;
    private Texture flatGround;
    private Texture reverseTank1;
    private int turn;
    private int count = 0;
    private Sprite revTanksprite;

    Float pos;


    public PlayScreen(final tankStars game, float pos,Texture tank,Texture revTank){
        this.game=game;
        stage = new Stage(new ScreenViewport());
        pausebutton = new Texture("pause.jpg");
        tank1 = new Tank1(50,80,tank,true,false);
        tank2 = new Tank1(550,80,tank,false,true);
        atlas = new TextureAtlas("tanks_pics.pack");


        flatGround = new Texture("flatGround.png");
        reverseTank1 = new Texture("reverseTank1.png");
        sprite = new Sprite();
        gamecam=new OrthographicCamera();

        revTanksprite = new Sprite(tank1.getTank());
        revTanksprite.flip(true,false);

        //gamecam.zoom-=0.3;
        gamePort=new ExtendViewport(850,480,gamecam);
        gamePort.apply();
        backGround = new Texture("background.jpg");
        ground=new Texture("map.png");

      //  hud = new hud(game.sprite);
        shapeRenderer = new ShapeRenderer();
        world=new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();
        this.pos=pos;
        player = new Tank(world,pos,this,250,250,tank);
        turn=0;

       // player= new Tank(world);

//        maploader=new TmxMapLoader();
//        map=maploader.load("map.jpg");
//        renderer=new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),0);




    }
    public void handleinput(float dt){


        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && player.character.getLinearVelocity().x <=2){
            player.character.applyLinearImpulse(new Vector2(0.1f,0),player.character.getWorldCenter(),true);
            if(turn==0) {
                tank1.movef();
            }
            else{
                tank2.movef();
            }

        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && player.character.getLinearVelocity().x >=-2){
            player.character.applyLinearImpulse(new Vector2(-0.1f,0),player.character.getWorldCenter(),true);
            if(turn==0) {
                tank1.moveb();
            }
            else{
                tank2.moveb();
            }

        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.S) && player.character.getLinearVelocity().x <=2) {
            player.character.applyLinearImpulse(new Vector2(0.1f, 0), player.character.getWorldCenter(), true);
//            if(turn==0) {
//                tank1.moves();
//            }
//            else{
//                tank2.moves();
//            }
            tank1.moves();
            tank2.moves();
            if(turn==0){
                turn = 1;
            }
            else if(turn==1){
                turn = 0;
            }

        }


    }
    public void update(float dt){

        handleinput(dt);
        if(turn==0 ){
            tank1.update(dt);

        }
        else {
            tank2.update(dt);

        }
//        if(turn==0 && tank1.getFuel()>0){
//            System.out.println(tank1.getFuel());
//            tank1.update(dt);
//           // tank2.setFuel(100);
//            if(tank1.getFuel()<=0){
//                turn=1;
//            }
//        }

        world.step(1/60f,6,2);
        player.update(dt);
       // player.character.applyForceToCenter(player.movement,true);
     //   gamecam.position.x = player.character.getPosition().x;
        //hud.stage.getCamera().position.x = player.character.getPosition().x;
        gamecam.update();
      //  renderer.setView(gamecam);

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {
        Drawable drawable = new TextureRegionDrawable(pausebutton);
        Gdx.input.setInputProcessor(stage);
        pauseButton = new ImageButton(drawable);
        pauseButton.setSize(45,45);
        pauseButton.setPosition(45,420);
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

    @Override
    public void render(float delta) {
        
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.sprite.setProjectionMatrix(gamecam.combined);
        game.sprite.begin();
        stage.act();

        game.sprite.draw(backGround,170,160,900,660);
//        game.sprite.draw(ground,220,160,850,200);
        game.sprite.draw(flatGround,220,160,950,100);
//        game.sprite.draw(player.getTank(),player.getPosition().x,player.getPosition().y,25,25);

        game.sprite.draw(tank1.getTank(),tank2.getPosition().x,tank2.getPosition().y,tank1.getTank().getWidth(),tank1.getTank().getHeight(),0,0,72,44,true,false);
        game.sprite.draw(tank1.getTank(),tank1.getPosition().x,tank1.getPosition().y);
//        game.sprite.draw(reverseTank1,200,500);
//        game.sprite.draw(player.getTankStand(),300,220,50,50);
//        player.render(game.sprite);
        if(tank1.getPosition().x < 10){
            tank1.getPosition().x = 10;
        }
        if(tank1.getPosition().x > 550){
            tank1.getPosition().x = 550;
        }
        if(tank2.getPosition().x < 10){
            tank2.getPosition().x = 10;
        }
        if(tank2.getPosition().x > 550){
            tank2.getPosition().x = 550;
        }
        stage.draw();
        game.sprite.end();

       // System.out.println(player.character.getUserData());
       // gamecam.position.x = player.movement.x;
       // gamecam.zoom+=100;
     
//
    //    hud.showHealth();
//
//
//        b2dr.render(player.world,player.gamecam.combined);
//        game.sprite.setProjectionMatrix(player.gamecam.combined);
//        game.sprite.setProjectionMatrix(hud.stage.getCamera().combined);
       // System.out.println(player.character.getPosition().x);


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
