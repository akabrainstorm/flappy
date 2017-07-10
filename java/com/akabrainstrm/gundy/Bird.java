package com.akabrainstrm.gundy;

import android.util.Log;

import com.akabrainstrm.gundy.core.Component;
import com.akabrainstrm.gundy.core.Physics;
import com.akabrainstrm.gundy.core.Sprite;
import com.akabrainstrm.gundy.core.SpriteRenderer;

class Bird extends Component {

    float vy = 0.0f;
    public SpriteRenderer[] pipes;
    Sprite me;

    @Override
    public void Update(float deltaTime) {
        vy -= 790.0f * deltaTime;
        gameObject.y += vy * deltaTime;

        if (gameObject.y < -300.0) {
            gameObject.y = 0.0f;
            vy = 0.0f;
        }

        for (int i = 0; i < pipes.length; i++) {
            float left = pipes[i].gameObject.x + pipes[i].offsetX - pipes[i].sprite.width / 2;
            float right = pipes[i].gameObject.x + pipes[i].offsetX + pipes[i].sprite.width / 2;
            float top = pipes[i].gameObject.y + pipes[i].offsetY + pipes[i].sprite.height / 2;
            float bottom = pipes[i].gameObject.y + pipes[i].offsetY - pipes[i].sprite.height / 2;

            if (Physics.AABBvsSphere(gameObject.x, gameObject.y, 8, left, top, right, bottom)) {
                gameObject.y = 0;
                break;
            }
        }
    }
}
