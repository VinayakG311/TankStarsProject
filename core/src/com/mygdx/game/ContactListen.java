package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Sprites.Tanktry;


public class ContactListen implements com.badlogic.gdx.physics.box2d.ContactListener {
    private Tanktry p1;
    private Tanktry p2;

    @Override
    public void beginContact(Contact contact) {
        //  System.out.println("Contact");
        final Body a=contact.getFixtureA().getBody();
        final Body b=contact.getFixtureB().getBody();

//        Body c = null;
//        Body d = null;
//        if(a.getUserData()=="tank"){
//            c=a;
//            System.out.println("hi1");
//        }
//        if(b.getUserData()=="tank"){
//            d=b;
//            System.out.println("hi2");
//        }
//        if(c!=null && d!=null) {
//            System.out.println(c.getPosition() + " " + d.getPosition());
//        }
        if(a.getUserData()=="missile" || b.getUserData()=="missile"){
            if(a.getUserData()=="missile"){

                if(b.getUserData()=="tank"){
                    System.out.println("tank hit");
                }
                if(b.getUserData()=="ground"){
                    System.out.println("ground hit");
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            if(Math.abs(a.getPosition().x-p2.getX())<=100){
                                p2.body.setLinearVelocity(new Vector2(100,0));
                            }
                            if(Math.abs(a.getPosition().x-p1.getX())<=100){
                                p1.body.setLinearVelocity(new Vector2(-100,0));
                            }
                            a.setTransform(0,0,0);
                        }
                    });
                }
            }
            if(b.getUserData()=="missile"){
                if(a.getUserData()=="tank"){

                    System.out.println("tank hit");
                }
                if(a.getUserData()=="ground"){
                    System.out.println("ground hit");
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            //    System.out.println(b.getPosition()+" "+p2.getX()+" "+p2.getY());
                            if(Math.abs(b.getPosition().x-p2.getX())<=100){
                                p2.body.setLinearVelocity(new Vector2(100,0));
                            }
                            if(Math.abs(b.getPosition().x-p1.getX())<=100){
                                p1.body.setLinearVelocity(new Vector2(-100,0));
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
    public void getplayers(Tanktry p1,Tanktry p2){
        this.p1=p1;
        this.p2=p2;

    }

}
