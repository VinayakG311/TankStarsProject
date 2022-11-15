package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class hud {
    public Stage stage;
    public Viewport viewport;
    private int health;
    public Label p1;
    public Label p2;
    public Label title;

    public hud(SpriteBatch spriteBatch){
        viewport = new FitViewport(800,480,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        // yahan pe bas hud ke elements ki image dalni h - title is just for example

//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.rect(0, 0, 5, 5);
//        shapeRenderer.end();




        Skin skin=new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        TextureRegionDrawable texturebar = new TextureRegionDrawable(new TextureRegion(new Texture("healthbar.jpeg")));
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white",Color.DARK_GRAY),texturebar);
        ProgressBar progressBar = new ProgressBar(0,10,0.5f,false,progressBarStyle);
        progressBar.setPosition(380,405);
        progressBar.setSize(1,5);

        progressBar.setAnimateDuration(0.25f);
        stage.addActor(progressBar);

//        Texture texture = new Texture("title.png");
//        Table table=new Table();
//        table.top();
//        table.setFillParent(true);
//        table.add(new Image(shapeRenderer));
        //stage.addActor(table);
//
////        table.add(new Image(texture)).width(200).height(100);
//        table.add(new Image(texture)).width(200).height(100).padTop(10);
//        stage.addActor(table);



    }

}
