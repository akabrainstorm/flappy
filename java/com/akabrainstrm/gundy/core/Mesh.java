package com.akabrainstrm.gundy.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

class Mesh {

    FloatBuffer vertexBuffer, uvBuffer;
    ShortBuffer indexBuffer;

    int indexCount;

    public void SetVertices(float[] vertices) {
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void SetIndices(short[] indices) {
        ByteBuffer dlb = ByteBuffer.allocateDirect(indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indexBuffer = dlb.asShortBuffer();
        indexBuffer.put(indices);
        indexBuffer.position(0);

        indexCount = indices.length;
    }

    public void SetUV(float[] uv) {
        ByteBuffer bb = ByteBuffer.allocateDirect(uv.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uv);
        uvBuffer.position(0);
    }

    static Mesh quad;

    static void Init() {
        quad = new Mesh();
        quad.SetVertices(new float[]{-0.5f,0.5f,0.0f,-0.5f,-0.5f,0.0f,0.5f,-0.5f,0.0f,0.5f,0.5f,0.0f});
        quad.SetIndices(new short[]{0,1,2,0,2,3});
    }
}
