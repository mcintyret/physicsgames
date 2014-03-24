package com.mcintyret.physicsgames;

public enum NoOpResponse implements Response {
    INSTANCE;

    @Override
    public void doResponse(GUI gui) {
        // DOES NOTHING!!!
    }
}
