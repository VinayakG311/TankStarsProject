package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.states.saveload;
import com.mygdx.game.tankStars;

public class Loadgame  implements Screen {

    private Preferences preferences;
    private com.mygdx.game.states.saveload saveload;
    private tankStars game;
    private Stage stage;
    private ImageButton load;
    private Texture cont;
    private Texture img;
    public Loadgame(tankStars game){
        saveload=new saveload();
        preferences= Gdx.app.getPreferences("saveload");
        cont=new Texture("bullet.png");
        stage=new Stage();
        this.game=game;
        Gdx.input.setInputProcessor(stage);
        img = new Texture("background.png");


    }
    @Override
    public void show() {
        Drawable drawable1= new TextureRegionDrawable(cont);
        Gdx.input.setInputProcessor(stage);
        load=new ImageButton(drawable1);
        load.setSize(45,45);
        load.setPosition(45,500);
        load.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("player1 "+saveload.getTexture(1,1)+" "+saveload.gethealth(1,1)+" "+saveload.getposX(1,1)+" "+saveload.getposY(1,1)+" "+saveload.getAngle(1,1));
                System.out.println("player2 "+saveload.getTexture(1,2)+" "+saveload.gethealth(1,2)+" "+saveload.getposX(1,2)+" "+saveload.getposY(1,2)+" "+saveload.getAngle(1,2));
                game.setScreen(new trialMapScreen(game,saveload.getAngle(1,1),saveload.gethealth(1,1),saveload.getposX(1,1),saveload.getposY(1,1),saveload.getTexture(1,1),saveload.getTexture(1,2),saveload.getAngle(1,2),saveload.gethealth(1,2),saveload.getposX(1,2),saveload.getposY(1,2)));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //    saveload.setstate(trialMapScreen.this);
                System.out.println("hi");
                return true;
            }
        });

        stage.addActor(load);
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
