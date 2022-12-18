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


    private Label outputLabel;
    private Texture revTank;

    private Texture tank1;

    private Texture contiue;
    private ImageButton newgameb;
    private ImageButton continuegameb;
    private Texture logo;



    public mainMenu(final tankStars game) {
        super();

        this.game = game;
        logo =  new Texture("logo1.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        img = new Texture("background.png");
        newGame = new Texture("buttonnewGame.png");
        contiue = new Texture("buttonContinue.png");
        skin= new Skin();
        skin.add("white","newGame.png");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font= new BitmapFont();
        revTank = new Texture("reverseTank1.png");
        tank1 = new Texture("tank1.png");

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

        Drawable newGameButton = new TextureRegionDrawable(newGame);
        Drawable continueGameButton = new TextureRegionDrawable(contiue);

        newgameb = new ImageButton(newGameButton);
        newgameb.setSize((float) (col_width*8), (float) (row_height*1.5));
        newgameb.setPosition(200,220);
        newgameb.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new chooseTank(game));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new chooseTank(game));
                return true;
            }
        });


        stage.addActor(newgameb);

        continuegameb = new ImageButton(continueGameButton);
        continuegameb.setSize((float) (col_width*8), (float) (row_height*1.5));
        continuegameb.setPosition(200,50);
       continuegameb.addListener(new ClickListener(){

           @Override
           public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
               game.setScreen(new Loadgame(game));
           }
           @Override
           public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
               game.setScreen(new Loadgame(game));
               return true;
           }
        });


        stage.addActor(continuegameb);


    }







    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(img,0,0,1600,620);
        stage.getBatch().draw(logo,440,400,300,200);
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
