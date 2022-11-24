package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Sprites.Tank1;
import com.mygdx.game.Sprites.Tanktry;

public class trialMapScreen implements Screen {
    tankStars game;
    World world;
    Body body;
    Body b2body;
    Box2DDebugRenderer b2dr;
    private Viewport camPort;

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    TmxMapLoader loader;
    Tank1 tank1;
    Tank tank;
    private Tanktry player;


    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / 100,
                (rectangle.y + rectangle.height * 0.5f ) / 100);
        polygon.setAsBox(rectangle.width * 0.5f /100,
                rectangle.height * 0.5f / 100,
                size,
                0.0f);
        return polygon;


    }

    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius);
        circleShape.setPosition(new Vector2(circle.x, circle.y));
        return circleShape;
    }

    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i];
        }

        polygon.set(worldVertices);
        return polygon;
    }


    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2];
            worldVertices[i].y = vertices[i * 2 + 1] ;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }


    public trialMapScreen(tankStars game){
        this.game=game;

        camera=new OrthographicCamera();


        camPort = new FitViewport(400,200,camera);

        tiledMap= new TmxMapLoader().load("newMap.tmx");
        camera.position.set(600,350,0);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        world = new World(new Vector2(0,-100),true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape2 = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;




//        for(MapObject object : tiledMap.getLayers().get(1).getObjects().getByType(PolygonMapObject.class)){
//            Polygon poly = ((PolygonMapObject) object).getPolygon();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set(poly.getX()+16,poly.getY()+16);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(16,16);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }


        for(MapObject object : tiledMap.getLayers().get(1).getObjects()){

            Shape shape;
            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject) object);
            } else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject) object);
            } else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject) object);
            } else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject) object);
            } else {
                continue;
            }
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        player = new Tanktry(world);



    }
    @Override
    public void show() {

    }


    public void handleInput(float dt){

        if(Gdx.input.isTouched()){
            camera.position.x+=100*dt;
            camera.update();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x <=4){
            player.b2body.applyLinearImpulse(new Vector2(0.1f,-0.1f),player.b2body.getWorldCenter(),true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <=-4){
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(),true);
        }



    }

    public void update(float dt){
        handleInput(dt);

        world.step(1/60f,6,2);

//        camera.position.x = player.b2body.getPosition().x;

//        tank1.setPosition(new Vector3((float) (body.getPosition().x-tank1.getTank().getWidth()/2), (float) (body.getPosition().y-1.3*tank1.getTank().getHeight()),0));

        camera.update();

    }
    @Override
    public void render(float delta) {
        this.update(delta);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        renderer.setView(camera);
        b2dr.render(world,camera.combined);

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
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
