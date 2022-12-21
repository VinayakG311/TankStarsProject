    package com.mygdx.game.Sprites;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.Sprite;
    import com.badlogic.gdx.physics.box2d.*;
    import com.mygdx.game.Screens.trialMapScreen;

    public class Tanktry extends Sprite {
        public World world;
        private double power;

        public int fire;
        public Body body;
        private Texture player1;
        private double angle;
        private double health;
        private String t;

        public Tanktry(World world, trialMapScreen screen,Float posx,Float posy,Texture tank,String t){
            this.t=t;

            this.world = world;
            createTank(posx,posy);
            player1 = tank;
            setBounds(300,320,player1.getWidth(), player1.getHeight());
            setRegion(player1);
            power=100;
            angle=45;
            health=175;
        }

        public void setHealth(double health) {
            this.health = health;
        }
        public double getHealth(){
            return health;
        }
        public double getpower(){
            return power;
        }
        public void setPower(double power1){
            power=power1;
        }


        public void update(float dt){

            setPosition(body.getPosition().x-getWidth() /2, body.getPosition().y-getHeight() / 2);
        }

        public void createTank(Float x,Float y){
            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(x ,y );
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            CircleShape shape = new CircleShape();
            shape.setRadius(15);

            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
            body.setUserData("tank");
        }
        public String getT(){
            return t;
        }
        public void setAngle(double angle) {
            this.angle = angle;
        }

        public double getAngle() {
            return angle;
        }
        public void toggle(){
            if(fire==0){
                fire=1;
            }
            else{
                fire=0;
            }
        }
    //    public void setx(Float x){
    //
    //    }
    }
