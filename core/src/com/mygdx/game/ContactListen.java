package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Scenes.hud;
import com.mygdx.game.Sprites.Tanktry;


public class ContactListen implements com.badlogic.gdx.physics.box2d.ContactListener {
    private Tanktry p1;
    private Tanktry p2;
    private com.mygdx.game.Scenes.hud hud;
    @Override
    public void beginContact(Contact contact) {
        //  System.out.println("Contact");
        final Body a=contact.getFixtureA().getBody();
        final Body b=contact.getFixtureB().getBody();

        if(a.getUserData()=="missile" || b.getUserData()=="missile"){
            if(a.getUserData()=="missile"){
                if(b.getUserData()=="ground" || b.getUserData()=="tank"){
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            if(Math.abs(a.getPosition().x-p2.getX())<=100){
                                p2.toggle();
                                float x=Math.abs(a.getPosition().x-p2.getX());
                                float healthred= 30*(100-x)/100;
                         //       System.out.println(healthred);

                                if((a.getPosition().x-p2.getX()<=100)&& (a.getPosition().x-p2.getX()>=0) ){
                                    p2.setHealth(p2.getHealth()-healthred);
                                    p2.body.setLinearVelocity(new Vector2(-100,0));


                                }
                                else{
                                    p2.setHealth(p2.getHealth()-healthred);
                                    p2.body.setLinearVelocity(new Vector2(100,0));

                                }
                            }
                            if(Math.abs(a.getPosition().x-p1.getX())<=100){
                                p1.toggle();
                                float x=Math.abs(a.getPosition().x-p1.getX());
                                float healthred= 30*(100-x)/100;
                           //     System.out.println(healthred);
                                if((a.getPosition().x-p1.getX()<=100)&& (a.getPosition().x-p1.getX()>=0) ){
                                    p1.setHealth(p1.getHealth()-healthred);
                                    p1.body.setLinearVelocity(new Vector2(-100,0));
                                }
                                else{
                                    p1.setHealth(p1.getHealth()-healthred);
                                    p1.body.setLinearVelocity(new Vector2(100,0));

                                }
                            }
                            a.setTransform(0,0,0);
                        }
                    });
                }
            }
            if(b.getUserData()=="missile"){
//                if(a.getUserData()=="tank"){
//
//                    System.out.println("tank hit");
//                }
                if(a.getUserData()=="ground" || a.getUserData()=="tank"){

                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            //    System.out.println(b.getPosition()+" "+p2.getX()+" "+p2.getY());
                            if(Math.abs(b.getPosition().x-p2.getX())<=100){
                                p2.toggle();
                                float x=Math.abs(b.getPosition().x-p2.getX());
                                float healthred= 30*(100-x)/100;

                                if(b.getPosition().x-p2.getX()<=100 && (b.getPosition().x-p2.getX()>=0)){
                                    p2.setHealth(p2.getHealth()-healthred);
                                    p2.body.setLinearVelocity(new Vector2(-100,0));
                                }
                                else{
                                    p2.setHealth(p2.getHealth()-healthred);
                                    p2.body.setLinearVelocity(new Vector2(100,0));

                                }
                            }
                            if(Math.abs(b.getPosition().x-p1.getX())<=100){
                                p1.toggle();
                                float x=Math.abs(b.getPosition().x-p1.getX());
                                float healthred= 30*(100-x)/100;
                                if((b.getPosition().x-p1.getX()<=100)&& (b.getPosition().x-p1.getX()>=0) ){
                                    p1.setHealth(p1.getHealth()-healthred);
                                    p1.body.setLinearVelocity(new Vector2(-100,0));
                                }
                                else{
                                    p1.setHealth(p1.getHealth()-healthred);
                                    p1.body.setLinearVelocity(new Vector2(100,0));

                                }
                            }
                            b.setTransform(0,0,0);

                        }
                    });

                }
            }
            // System.out.println("missile hit ");
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    public void getplayers(Tanktry p1,Tanktry p2,hud hud){
        this.p1=p1;
        this.p2=p2;
        this.hud=hud;

    }

}
