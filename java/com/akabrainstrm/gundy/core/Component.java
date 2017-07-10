package com.akabrainstrm.gundy.core;

public abstract class Component {
    public GameObject gameObject;

    public void Start() {}
    public void Update(float deltaTime) {}
    public void Render(float[] mvpMatrix) {}
    void OnCreate(GameObject instance) {
        gameObject = instance;
    }
}
