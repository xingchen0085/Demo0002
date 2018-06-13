package com.myxinge.demo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Main implements ApplicationListener {

    private SpriteBatch spriteBatch;//精灵

    private Texture dropTexture;//雨滴
    private Texture bucketTexture;//篮子
    private Sound dropSound;//水滴声
    private Music rainMusic;//下雨音乐

    private OrthographicCamera camera;//摄像机

    private Rectangle bucket;

    private Array<Rectangle> raindrops;

    private long lastDropTime;

    @Override
    public void create() {
        Gdx.app.log("Main.java", "初始化");
        spriteBatch = new SpriteBatch();
        //初始化纹理等
        dropTexture = new Texture(Gdx.files.internal("texture/droplet.png"));
        bucketTexture = new Texture(Gdx.files.internal("texture/bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("sound/drop.mp3"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/rain.mp3"));

        //开始播放背景音乐
        rainMusic.setLooping(true);//循环播放
        rainMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 568, 360);

        bucket = new Rectangle();
        bucket.x = 568 / 2 - 64 / 2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    @Override
    public void resize(int i, int i1) {
        Gdx.app.log("Main.java", "窗口更新大小:[x = " + i + ",y=" + i1 + "]");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        //动起来
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 500 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 500 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) bucket.y += 500 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) bucket.y -= 500 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0) bucket.x = 0;
        if (bucket.x > 568 - 64) bucket.x = 568 - 64;
        if (bucket.y < 0) bucket.y = 0;
        if (bucket.y > 360 - 64) bucket.y = 360 - 64;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0) iter.remove();
            if (raindrop.overlaps(bucket)) {//接到了
                dropSound.play();
                iter.remove();
            }
        }

        spriteBatch.begin();
        spriteBatch.draw(bucketTexture, bucket.x, bucket.y);
        for (Rectangle raindrop : raindrops) {
            spriteBatch.draw(dropTexture, raindrop.x, raindrop.y);
        }
        spriteBatch.end();

        Gdx.app.log("Main.java", "雨滴数量:" + raindrops.size);
    }

    @Override
    public void pause() {
        Gdx.app.log("Main.java", "暂停..");
    }

    @Override
    public void resume() {
        Gdx.app.log("Main.java", "恢复..");
    }

    @Override
    public void dispose() {
        Gdx.app.log("Main.java", "释放资源..");
        rainMusic.stop();
        dropTexture.dispose();
        dropSound.dispose();
        bucketTexture.dispose();
        rainMusic.dispose();
        spriteBatch.dispose();
    }

    //下雨
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 568 - 64);
        raindrop.y = 360;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
}
