package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Scenes.hud;
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


    public PlayScreen(tankStars game){
        this.game=game;
        gamecam=new OrthographicCamera();
        gamePort=new ExtendViewport(850,500,gamecam);
        backGround = new Texture("background.jpg");
        ground=new Texture("map.png");
        
        hud = new hud(game.sprite);
        bf_loadProgress = new BitmapFont();
        bf_loadProgress.getData().setScale(2,1);
        shapeRenderer = new ShapeRenderer();
//        maploader=new TmxMapLoader();
//        map=maploader.load("map.jpg");
//        renderer=new OrthogonalTiledMapRenderer(map);
//        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);


    }
    public void handleinput(float dt){
        if(Gdx.input.isTouched()){
            gamecam.position.x+=100*dt;
        }

    }
    public void update(float dt){
        handleinput(dt);

        world.step(1/60f,6,2)
        gamecam.update();
      //  renderer.setView(gamecam);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       // renderer.render();
        //   game.sprite.setProjectionMatrix(gamecam.combined);
        game.sprite.begin();
        game.sprite.draw(backGround,0,0,850,500);
        game.sprite.draw(ground,0,0,850,100);
        game.sprite.end();
        showHealth();
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
