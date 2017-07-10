package com.akabrainstrm.gundy.core;

import java.util.Map;

public class TextRenderer extends Component {

    Texture fontAtlas;
    Map<Character, float[]> font;

    public TextRenderer(Texture fontAtlas, Map<Character, float[]> font) {
        this.fontAtlas = fontAtlas;
        this.font = font;
    }
}
