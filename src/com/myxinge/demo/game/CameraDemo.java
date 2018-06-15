package com.myxinge.demo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

//摄像机demo
public class CameraDemo implements ApplicationListener {

    private static int WORLD_WIDTH = 100;
    private static int WORLD_HEIGHT = 100;

    //精灵
    private Sprite sprite;
    //精灵画笔
    private SpriteBatch batch;
    //摄像机
    private OrthographicCamera camera;

    private float rotationSpeed;

    @Override
    public void create() {
        rotationSpeed = 0.5f;
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("texture/map.png")));
        sprite.setPosition(0, 0);//最左边
        sprite.setSize(WORLD_WIDTH, WORLD_HEIGHT);


        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        //摄像机视图
        camera = new OrthographicCamera(30, 30 * (w / h));
        //设置相机初始位置
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);//x , y ,z
        camera.update();
    }

    @Override
    public void render() {
        //处理键盘事件
        handlerInput();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //画地图
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {
        camera.viewportWidth = 30f;
        camera.viewportHeight =30 * i/i1;
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        batch.dispose();
    }

    private void handlerInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //相机左移
            camera.translate(-1, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(1, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 1, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -1, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            //放大
            camera.zoom -= 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.C)){
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.translate(0, 0, 1);
        }

        //限制不能一直放大和缩小
        camera.zoom = MathUtils.clamp(camera.zoom,0.1f,100/camera.viewportWidth);
        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;
        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
}
