package com.akabrainstrm.gundy.core;

import java.util.ArrayList;
import java.util.List;

public final class GameObject {
    public float x, y, z;

    List<Component> components = new ArrayList<Component>();

    public void AddComponent(Component component) {
        component.OnCreate(this);
        components.add(component);
    }
}
