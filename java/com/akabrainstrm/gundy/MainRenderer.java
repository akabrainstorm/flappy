package com.akabrainstrm.gundy;

import android.content.Context;
import android.view.MotionEvent;

import com.akabrainstrm.gundy.core.GameObject;
import com.akabrainstrm.gundy.core.GameRenderer;
import com.akabrainstrm.gundy.core.Sprite;
import com.akabrainstrm.gundy.core.SpriteRenderer;
import com.akabrainstrm.gundy.core.Texture;

import java.util.HashMap;
import java.util.Map;

class MainRenderer extends GameRenderer {

    MainRenderer(Context ctx) {
        super(ctx);
    }

    private Bird birdCmp;

    @Override
    public void Init() {
        Texture atlas = new Texture(ctx, R.drawable.atlas);
        Sprite background = new Sprite(atlas, 0, 0, 288, 512);
        Sprite terrainSprite = new Sprite(atlas, 584, 0, 920, 112);
        Sprite pipeSprite = new Sprite(atlas, 168, 646, 220, 966);
        Sprite pipe2Sprite = new Sprite(atlas, 112, 646, 164, 966);
        Sprite birdSprite = new Sprite(atlas, 62, 982, 96, 1006);
        //Sprite coinSprite = new Sprite(atlas, 223, 268, 953, 998);

        // Font
        Map<Character, int[]> numberFont = new HashMap<>();

        numberFont.put('0', new int[] { 991, 119, 1016, 156 });
        numberFont.put('2', new int[] { 583, 319, 608, 356 });
        numberFont.put('3', new int[] { 611, 319, 636, 356 });

        GameObject bg = Instantiate();
        GameObject terrain = Instantiate();
        GameObject bird = Instantiate();

        bg.z = -1.0f;
        terrain.z = 1.0f;
        terrain.y = -200.0f;

        bg.AddComponent(new SpriteRenderer(background));
        terrain.AddComponent(new SpriteRenderer(terrainSprite));

        SpriteRenderer birdRend = new SpriteRenderer(birdSprite);
        bird.AddComponent(birdRend);
        birdCmp = new Bird();
        bird.AddComponent(birdCmp);

        birdCmp.me = birdSprite;
        birdCmp.pipes = new SpriteRenderer[10];
        for(int i = 0; i < 5; i++)
        {
            GameObject pipe = Instantiate();
            pipe.x = i * 140.0f;
            pipe.y = Pipe.RandomPoint();
            //pipe.y = -64.0f;
            //pipe.y = 160.0f;

            SpriteRenderer down = new SpriteRenderer(pipeSprite);
            SpriteRenderer up = new SpriteRenderer(pipe2Sprite);

            down.offsetY = -160.0f - 48.0f;
            up.offsetY = 160.0f + 48.0f;

            pipe.AddComponent(down);
            pipe.AddComponent(up);
            pipe.AddComponent(new Pipe());

            birdCmp.pipes[i*2] = down;
            birdCmp.pipes[i*2+1] = up;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            birdCmp.vy = 300.0f;
        }
        return true;
    }
}
