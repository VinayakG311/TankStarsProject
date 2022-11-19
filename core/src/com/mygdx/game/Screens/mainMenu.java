package com.mygdx.game.Screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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





    public mainMenu(final tankStars game) {
        super();

        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        img = new Texture("background.png");
        newGame = new Texture("newGame.png");
        skin= new Skin();
        skin.add("white","newGame.png");
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font= new BitmapFont();


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

        // Button
        Button button1 = new TextButton("NEW GAME",mySkin);
        button1.setSize(col_width*8,row_height*2);
        button1.setPosition(100,250);
        button1.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game,0f));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game,0f));
                return true;
            }
        });
        stage.addActor(button1);

        // Text Button
        Button button2 = new TextButton("CONTINUE",mySkin);
        button2.setSize(col_width*8,row_height*2);
        button2.setPosition(100,125);
        button2.addListener(new ClickListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game,0f));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game,0f));
                return true;
            }
        });
        stage.addActor(button2);

    }





//    @Override
//    public void show() {
//        sb = new SpriteBatch();
//        Texture img = new Texture("background.png");
//        sprite = new Sprite(img);
//        Texture newGame = new Texture("newGame.png");
//        sprite2 = new Sprite(newGame);
//        sprite.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//
//
//        sprite2.setBounds(Gdx.graphics.getWidth()/2-125,Gdx.graphics.getHeight()/2,250,40 );
//
////        newGame = new Texture("newGame.png");
////        ImageButton button1 = new ImageButton((Drawable) newGame);
////        button1.setBounds(Gdx.graphics.getWidth()/2-125,Gdx.graphics.getHeight()/2,250,40);
////        button1.addListener(new InputListener(){
////            @Override
////            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
////                game.setScreen(new PlayScreen(game));
////            }
////            @Override
////            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
////                game.setScreen(new PlayScreen(game));
////                return true;
////            }
////        });
////        stage.addActor(button1);
////        Gdx.input.setInputProcessor(new InputAdapter() {
////            @Override
////            public boolean keyDown(int keyCode) {
////                if (keyCode == Input.Keys.SPACE) {
////                    game.setScreen(new PlayScreen(game));
////                }
////                return true;
////            }
////        });
//    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        sb.begin();
//        sprite.draw(sb);
//        //sprite2.draw(sb);
//        sb.end();
//        stage.draw();

        stage.act();
        stage.getBatch().begin();
        stage.getBatch().draw(img,0,0,850,500);
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
