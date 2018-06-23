package com.myxinge.demo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myxinge.demo.game.AnimationDemo;
import com.myxinge.demo.game.CameraDemo;
import com.myxinge.demo.game.Main;
import com.myxinge.demo.game.RotationDemo;

public class DemoLauncher {
    public static void main(String[] args){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width=800;
        config.height=600;

//        config.resizable=false;

        new LwjglApplication(new RotationDemo(),config);
    }
}
