package com.myxinge.demo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

/**
 * 让纹理动起来
 */
public class RotationDemo implements ApplicationListener {

    private ShapeRenderer shapeRenderer;//画图
    private float radius;
    boolean zoomFlag = true;//放大为true
    private float max;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.RED);
        radius = 100;
        if (Gdx.graphics.getWidth() > Gdx.graphics.getHeight()) {
            max = Gdx.graphics.getHeight() / 2;
        } else {
            max = Gdx.graphics.getWidth() / 2;
        }
    }

    @Override
    public void resize(int i, int i1) {
        shapeRenderer.updateMatrices();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.app.log("1", "圆: " + radius);
        if (radius >= max) {
            zoomFlag = false;
        }
        if (radius <= 100) {
            zoomFlag = true;
        }

        if (zoomFlag) {
            radius = radius + 500 * Gdx.graphics.getDeltaTime();
        } else {
            radius = radius - 500 * Gdx.graphics.getDeltaTime();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, radius, 200);
        shapeRenderer.end();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
