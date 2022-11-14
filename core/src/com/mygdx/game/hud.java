package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
        Texture texture = new Texture("title.png");
        Table table=new Table();
        table.top();
        table.setFillParent(true);

        table.add(new Image(texture)).width(200).height(100);
        stage.addActor(table);

    }

}
