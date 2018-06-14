package com.myxinge.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myxinge.demo.game.Main;
import com.myxinge.demo.game.Main2;

public class DemoLauncher {
    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=360;
        config.height=128;

        config.resizable=false;

        new LwjglApplication(new Main2(),config);
    }
}
