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

        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();

        TextureRegionDrawable texturebar = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background=texturebar;
        pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        texturebar = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knob = texturebar;

        pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        texturebar = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = texturebar;
        ProgressBar progressBar = new ProgressBar(0.0f,1.0f,0.1f,false,progressBarStyle);
       // progressBar.setPosition(380,405);
        progressBar.setBounds(230, 405, 100, 20);
        progressBar.setAnimateDuration(0.25f);
        stage.addActor(progressBar);
        ProgressBar progressBar1 = new ProgressBar(0.0f,1.0f,0.1f,false,progressBarStyle);
        progressBar1.setBounds(420, 405, 100, 20);
        
        stage.addActor(progressBar1);

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
