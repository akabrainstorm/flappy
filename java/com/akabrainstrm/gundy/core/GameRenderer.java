package com.akabrainstrm.gundy.core;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.view.MotionEvent;

import com.akabrainstrm.gundy.MGLSurfaceView;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GameRenderer implements GLSurfaceView.Renderer {

    private List<GameObject> hierarchy = new ArrayList<>();

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private long lastTime;

    public Context ctx;

    public GameRenderer(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);

        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glDepthFunc(GLES20.GL_LESS);
        Mesh.Init();
        ShaderManager.Init();
        Init();

        // Call Start Function of all components
        for (GameObject go : hierarchy)
            for (Component comp : go.components)
                comp.Start();

        lastTime = System.nanoTime();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        float size = 240.0f;
        Matrix.orthoM(mProjectionMatrix, 0, -ratio*size, ratio*size, -size, size, 1, 20);

        // Set the camera position - view matrix
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, 10.0f, 0, 0, 0, 0, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        long currentTime = System.nanoTime();
        float deltaTime = (float) (currentTime - lastTime) / 1000000000.0f;
        lastTime = currentTime;

        // Update all components of game objects
        for (GameObject go : hierarchy)
            for (Component comp : go.components)
                comp.Update(deltaTime);

        // Render all objects
        for (GameObject go : hierarchy)
            for (Component comp : go.components)
                comp.Render(mMVPMatrix);
    }

    public GameObject Instantiate() {
        if (hierarchy.add(new GameObject()))
            return hierarchy.get(hierarchy.size() - 1);
        else
            return null;
    }

    public void Init(){}
    public boolean onTouchEvent(MotionEvent event){ return true; }
}
