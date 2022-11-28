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
    ShapeRenderer shapeRenderer;

    public hud(SpriteBatch spriteBatch){
        viewport = new FitViewport(800,480,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        shapeRenderer=new ShapeRenderer();

    }
    public void showHealth(){

        float health = 200;

        shapeRenderer.setProjectionMatrix(stage.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(465, 418, health, 30);
        shapeRenderer.rect(145, 418, health, 30);
        shapeRenderer.end();

    }

}
