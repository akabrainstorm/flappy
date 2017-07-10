package com.akabrainstrm.gundy;

import com.akabrainstrm.gundy.core.Component;

class Pipe extends Component {

    @Override
    public void Update(float deltaTime) {
        if (gameObject.x > -420.0f) {
            gameObject.x -= deltaTime*100;
        } else {
            gameObject.x += 700.0f;
            gameObject.y = RandomPoint();
        }
    }

    public static float RandomPoint() {
        return (float)(Math.random() * 224 - 64);
    }
}
