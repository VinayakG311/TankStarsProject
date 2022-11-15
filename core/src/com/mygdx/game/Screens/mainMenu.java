package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.tankStars;

public class mainMenu extends ScreenAdapter implements Screen {
    private tankStars game;
    private SpriteBatch sb;
    private Sprite sprite;
    private Sprite sprite2;


    private Texture newGame;

    private Texture texture;

    public Stage stage;

    private Texture img;
    public Skin skin;



    public mainMenu(tankStars game) {
        super();
        this.game = game;

        stage = new Stage();
        img = new Texture("background.png");
        newGame = new Texture("newGame.png");

    }





    @Override
    public void show() {
        sb = new SpriteBatch();
        Texture img = new Texture("background.png");
        sprite = new Sprite(img);
        Texture newGame = new Texture("newGame.png");
        sprite2 = new Sprite(newGame);
        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        sprite2.setBounds(Gdx.graphics.getWidth()/2-125,Gdx.graphics.getHeight()/2,250,40 );

        newGame = new Texture("newGame.png");
        ImageButton button1 = new ImageButton((Drawable) newGame);
        button1.setBounds(Gdx.graphics.getWidth()/2-125,Gdx.graphics.getHeight()/2,250,40);
        button1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });
        stage.addActor(button1);
//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.SPACE) {
//                    game.setScreen(new PlayScreen(game));
//                }
//                return true;
//            }
//        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sprite.draw(sb);
        sprite2.draw(sb);
        sb.end();
        stage.draw();
    }


    @Override
    public void resize(int width, int height) {

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
        sb.dispose();
        sprite.getTexture().dispose();
    }
}
