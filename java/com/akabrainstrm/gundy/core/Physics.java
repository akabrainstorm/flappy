package com.akabrainstrm.gundy.core;

public final class Physics {
    public static boolean AABBvsAABB(float aleft, float atop, float aright, float abottom, float bleft, float btop, float bright, float bbottom) {
        /*return (bleft > aright ||
                bright < aleft ||
                btop > abottom ||
                bbottom < atop);*/
        return Math.max(aleft, bleft) < Math.min(aright, bright) && Math.min(atop, btop) > Math.max(abottom, bbottom);
    }

    public static boolean AABBvsSphere(float x, float y, float radius, float left, float top, float right, float bottom) {
        float cx = Math.max(left, Math.min(x, right)) - x;
        float cy = Math.max(bottom, Math.min(y, top)) - y;

        return Math.sqrt(cx * cx + cy * cy) < radius;
    }
}
