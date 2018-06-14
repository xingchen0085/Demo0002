package com.myxinge.demo.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.net.HttpRequestBuilder;

/**
 * @Auth:chenxinghua
 * @Date:2018\6\14 0014 9:21
 * @Description:
 */
public class Main2 implements ApplicationListener {

    private Net.HttpResponseListener responseListener;
    private HttpRequestBuilder requestBuilder;
    private Net.HttpRequest request;

    @Override
    public void create() {
        requestBuilder = new HttpRequestBuilder();
        request = requestBuilder.newRequest().method("GET").url("http://www.baidu.com").build();

        responseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("Main2", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable throwable) {
                Gdx.app.error("Main2", "异常信息:", throwable);
            }

            @Override
            public void cancelled() {
                Gdx.app.error("Main2", "被取消.");
            }
        };

        Gdx.net.sendHttpRequest(request, responseListener);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.log("Main2", "Q keys ...");
        }

        if (Gdx.input.isTouched()) {
            Gdx.app.log("Main2", "touched..[" + Gdx.input.getX() + "," + Gdx.input.getY() + "]");
        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
