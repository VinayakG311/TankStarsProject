package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.tankStars;
import com.mygdx.game.trialMapScreen;

public class chooseTank implements Screen {
    private Stage stage;
    private Texture tank1;
    private Texture tank2;
    private Texture tank3;
    private Texture img;
    private ImageButton tank1button,tank2button,tank3button,tank4button;
    private tankStars game;
    private Texture revTank;

    public chooseTank(tankStars game){
        this.game = game;
        img = new Texture("background.png");
        stage = new Stage(new ScreenViewport());
        tank1 = new Texture("tank1.png");
        tank2 = new Texture("tank2.png");
        tank3 = new Texture("tank3.png");
        revTank = new Texture("reverseTank1.png");
    }

    @Override
    public void show() {

        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));

        Label title = new Label("CHOOSE YOUR TANK",mySkin);
        title.setSize(Gdx.graphics.getWidth(),row_height*2);
        title.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
        title.setAlignment(Align.center);
        stage.addActor(title);

        Drawable drawable = new TextureRegionDrawable(tank1);
        Gdx.input.setInputProcessor(stage);
        tank1button = new ImageButton(drawable);
        tank1button.setSize(100,100);
        tank1button.setPosition(450,300);
        tank1button.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank1;
                game.setScreen(new trialMapScreen(game,tank1,revTank));

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank1;
                game.setScreen(new trialMapScreen(game,tank1,revTank));
                return true;
            }
        });


        stage.addActor(tank1button);

        Drawable drawable2 = new TextureRegionDrawable(tank2);
        Gdx.input.setInputProcessor(stage);
        tank2button = new ImageButton(drawable2);
        tank2button.setSize(100,100);
        tank2button.setPosition(650,300);
        tank2button.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank2;
                game.setScreen(new trialMapScreen(game,tank2,revTank));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank2;
                game.setScreen(new trialMapScreen(game,tank2,revTank));
                return true;
            }
        });


        stage.addActor(tank2button);

        Drawable drawable3 = new TextureRegionDrawable(tank3);
        Gdx.input.setInputProcessor(stage);
        tank3button = new ImageButton(drawable3);
        tank3button.setSize(100,100);
        tank3button.setPosition(550,200);
        tank3button.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank3;
                game.setScreen(new trialMapScreen(game,tank3,revTank));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.tank = tank3;
                game.setScreen(new trialMapScreen(game,tank3,revTank));
                return true;
            }
        });


        stage.addActor(tank3button);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(img,0,0,1600,620);
        stage.getBatch().end();
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

    }
}
