package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.tankStars;

public class pauseScreen extends ScreenAdapter implements Screen {
    private tankStars game;
    private SpriteBatch sb;
    private Sprite sprite;
    private Sprite sprite2;


    private Texture newGame;

    private Texture texture;

    public Stage stage;

    private Texture img;
    public Skin skin;


    private Label outputLabel;
    private Texture revTank;
    private Texture exit;
    private Texture resume;
    private Texture save;
    private ImageButton exitb;
    private ImageButton resumeb;
    private ImageButton saveb;
    private Texture logo;
    private String t1,t2;


    public pauseScreen(final tankStars game,String t1,String t2) {
        super();
        this.t1=t1;
        this.t2=t2;


        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        img = new Texture("background.png");
        newGame = new Texture("newGame.png");
        resume = new Texture("buttonTalberesume.png");
        exit = new Texture("buttonTalbeexit.png");
        save = new Texture("buttonTalbesave.png");
        skin= new Skin();
        logo = new Texture("logo1.png");
        skin.add("white","newGame.png");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font= new BitmapFont();

        revTank = new Texture("reverseTank1.png");
    }


    @Override
    public void show () {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Texture img = new Texture("background.png");


        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;

        Skin mySkin = new Skin(Gdx.files.internal("skin/star-soldier-ui.json"));

        Label title = new Label("Tank Stars",mySkin);
        title.setSize(Gdx.graphics.getWidth(),row_height*2);
        title.setPosition(0,Gdx.graphics.getHeight()-row_height*2);
        title.setAlignment(Align.center);
        stage.addActor(title);

        Drawable exitbutton = new TextureRegionDrawable(exit);
        Drawable resumebutton = new TextureRegionDrawable(resume);
        Drawable savebutton = new TextureRegionDrawable(save);

        exitb = new ImageButton(exitbutton);
        resumeb = new ImageButton(resumebutton);
        saveb = new ImageButton(savebutton);


        exitb.setSize((float) (col_width*8), (float) (row_height));
        exitb.setPosition(200,270);
        exitb.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new mainMenu(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new mainMenu(game));
                return true;
            }
        });


        stage.addActor(exitb);
        resumeb.setSize((float) (col_width*8), (float) (row_height));
        resumeb.setPosition(200,150);
        resumeb.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new trialMapScreen(game,game.tank,game.tank,t1,t2));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new trialMapScreen(game,game.tank,game.tank,t1,t2));
                return true;
            }
        });


        stage.addActor(resumeb);

        saveb.setSize((float) (col_width*8), (float) (row_height));
        saveb.setPosition(200,30);
        saveb.addListener(new ClickListener(){

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new trialMapScreen(game,game.tank,game.tank,t1,t2));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new trialMapScreen(game,game.tank,game.tank,t1,t2));
                return true;
            }
        });


        stage.addActor(saveb);



    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(img,0,0,1600,620);
        stage.getBatch().draw(logo,500,440,200,100);
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
        sb.dispose();
        sprite.getTexture().dispose();
    }
}
