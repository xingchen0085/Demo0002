package com.myxinge.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myxinge.demo.game.Main;

public class DemoLauncher {
    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=568;
        config.height=360;

        config.resizable=false;

        new LwjglApplication(new Main(),config);
    }
}
