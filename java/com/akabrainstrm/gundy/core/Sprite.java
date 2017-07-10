package com.akabrainstrm.gundy.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Sprite {

    public Texture texture;
    final FloatBuffer uvBuffer;
    public final int width, height;

    public Sprite(Texture texture, int left, int top, int right, int bottom) {
        this.texture = texture;
        float uvLeft = (float) left / texture.width;
        float uvRight = (float) right / texture.width;
        float uvTop = (float) top / texture.height;
        float uvBottom = (float) bottom / texture.height;

        float[] uv = new float[] { uvLeft, uvTop, uvLeft, uvBottom, uvRight, uvBottom, uvRight, uvTop };

        ByteBuffer bb = ByteBuffer.allocateDirect(uv.length * 4);
        bb.order(ByteOrder.nativeOrder());
        uvBuffer = bb.asFloatBuffer();
        uvBuffer.put(uv);
        uvBuffer.position(0);

        width = right - left;
        height = bottom - top;
    }
}
