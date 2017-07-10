package com.akabrainstrm.gundy.core;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class SpriteRenderer extends Component {

    private final float[] modelMatrix = new float[16];

    public Sprite sprite;

    public float offsetX = 0;
    public float offsetY = 0;
    public float offsetZ = 0;

    public SpriteRenderer(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void Render(float[] mvpMatrix) {
        GLES20.glUseProgram(ShaderManager.spriteProgram);

        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, gameObject.x + offsetX, gameObject.y + offsetY, gameObject.z + offsetZ);
        Matrix.scaleM(modelMatrix,0, sprite.width, sprite.height, 1.0f);

        float[] tempMatrix = new float[16];
        Matrix.multiplyMM(tempMatrix, 0, mvpMatrix, 0, modelMatrix, 0);

        int mPositionHandle = GLES20.glGetAttribLocation(ShaderManager.spriteProgram, "a_Position");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 12, Mesh.quad.vertexBuffer);

        int mTextureCoordinatesHandle = GLES20.glGetAttribLocation(ShaderManager.spriteProgram, "a_TextureCoordinates");
        GLES20.glEnableVertexAttribArray(mTextureCoordinatesHandle);
        GLES20.glVertexAttribPointer(mTextureCoordinatesHandle, 2, GLES20.GL_FLOAT, false, 8, sprite.uvBuffer);

        int mMVPMatrixHandle = GLES20.glGetUniformLocation(ShaderManager.spriteProgram, "u_Matrix");
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, tempMatrix, 0);

        int mTextureUniformHandle = GLES20.glGetUniformLocation(ShaderManager.spriteProgram, "u_TextureUnit");

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, sprite.texture.textureId);
        GLES20.glUniform1i(mTextureUniformHandle, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, Mesh.quad.indexCount, GLES20.GL_UNSIGNED_SHORT, Mesh.quad.indexBuffer);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
        GLES20.glDisableVertexAttribArray(mTextureCoordinatesHandle);
    }
}
