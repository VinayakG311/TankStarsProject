package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.game.Screens.trialMapScreen;
import com.mygdx.game.Sprites.Tanktry;

import java.io.Serializable;

public class saveload implements Serializable {
    static int noofsaves=1;
    private Preferences preferences= Gdx.app.getPreferences("saveload");


    public void setstate(Tanktry player,int num){
        preferences.putString("save "+noofsaves+"player"+num+" health",new Json().toJson(player.getHealth()));
        preferences.putString("save "+noofsaves+"player"+num+" positionX",new Json().toJson(player.getX()));
        preferences.putString("save "+noofsaves+"player"+num+" positionY",new Json().toJson(player.getY()));
        preferences.putString("save "+noofsaves+"player"+num+" Angle",new Json().toJson(player.getAngle()));
//        preferences.putString("save "+noofsaves+"player2 health"+noofsaves,new Json().toJson(player.getHealth()));
//        preferences.putString("save "+noofsaves+"player2 positionX"+noofsaves,new Json().toJson(player.getX()));
//        preferences.putString("save "+noofsaves+"player2 positionY"+noofsaves,new Json().toJson(player.getY()));
//        preferences.putString("save "+noofsaves+"player2 Angle"+noofsaves,new Json().toJson(player.getAngle()));
        preferences.flush();
    }
    public Double gethealth(int savenumber,int num){
        final JsonValue jsonval= new JsonReader().parse(preferences.getString("save "+noofsaves+"player"+num+" health"));
        return new Json().fromJson(Double.class, String.valueOf(jsonval));

    }
    public Float getposX(int savenumber,int num){
        final JsonValue jsonval= new JsonReader().parse(preferences.getString("save "+noofsaves+"player"+num+" positionX"));
        return new Json().fromJson(Float.class, String.valueOf(jsonval));

    }
    public Float getposY(int savenumber,int num){
        final JsonValue jsonval= new JsonReader().parse(preferences.getString("save "+noofsaves+"player"+num+" positionY"));
        return new Json().fromJson(Float.class, String.valueOf(jsonval));

    }
    public Double getAngle(int savenumber,int num){
        final JsonValue jsonval= new JsonReader().parse(preferences.getString("save "+noofsaves+"player"+num+" Angle"));
        return new Json().fromJson(Double.class, String.valueOf(jsonval));

    }

}
