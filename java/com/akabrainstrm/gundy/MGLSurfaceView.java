package com.akabrainstrm.gundy;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MGLSurfaceView extends GLSurfaceView {

    private final MainRenderer gameScene;

    public MGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        gameScene = new MainRenderer(context);

        // Set the renderer for drawing on the GLSurfaceView
        setRenderer(gameScene);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gameScene.onTouchEvent(event);
    }
}
