package com.akabrainstrm.gundy.core;

import android.opengl.GLES20;

public final class ShaderManager {
    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int buildGLProgram(int vertexShader, int fragmentShader) {
        int mProgram = GLES20.glCreateProgram();

        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);
        return mProgram;
    }

    public static int spriteProgram;

    public static void Init() {
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, "uniform mat4 u_Matrix;attribute vec4 a_Position;attribute vec2 a_TextureCoordinates;varying vec2 v_TextureCoordinates;void main(){v_TextureCoordinates = a_TextureCoordinates;gl_Position = u_Matrix * a_Position;}");
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, "precision mediump float;uniform sampler2D u_TextureUnit;varying vec2 v_TextureCoordinates;void main(){vec4 val = texture2D(u_TextureUnit, v_TextureCoordinates);if(val.a > 0.5){gl_FragColor=val;}else{discard;}}");
        spriteProgram = buildGLProgram(vertexShader, fragmentShader);
    }
}
