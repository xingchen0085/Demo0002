package com.myxinge.demo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//动画测试
public class AnimationDemo implements ApplicationListener {
    private SpriteBatch spriteBatch;
    private Array<TextureRegion> textureRegionList;//纹理列表
    private Texture texture;
    private static int T_WIDTH = 48;
    private static int T_HEIGHT = 48;
    private TextureRegion textureRegion;

    //动画
    private Animation animation;

    private float stateTime;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
//        textureRegionList = new Array<TextureRegion>();
        texture = new Texture(Gdx.files.internal("texture/1525205509.png"));

//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                textureRegion = new TextureRegion(texture, i * 48, j * 48, T_WIDTH, T_HEIGHT);
//                textureRegionList.add(textureRegion);
//            }
//        }
        TextureRegion[][] textureRegions = TextureRegion.split(texture,
                (texture.getWidth() + 20) / 8, texture.getHeight() / 1);
        TextureRegion[] walkFrames = new TextureRegion[textureRegions[0].length];
        int index = 0;
        for (int i = 0; i < textureRegions[0].length; i++) {
            walkFrames[index++] = textureRegions[0][i];
        }
        animation = new Animation(0.07f, walkFrames);
        //循环
        animation.setPlayMode(Animation.PlayMode.LOOP);
        stateTime = 0f;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        spriteBatch.begin();
        TextureRegion keyFrame = animation.getKeyFrame(stateTime, true);
        if (null != keyFrame) {
            spriteBatch.draw(keyFrame, 100, 0);
        }
        spriteBatch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
}
