package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.GameDetails;
import com.mcintyret.physicsgames.GamePanel;

public enum HelicopterGameDetails implements GameDetails {
    INSTANCE;

    @Override
    public GamePanel newGamePanel() {
        return new HelicopterGamePanel();
    }

    @Override
    public String getGameName() {
        return "Helicopter";
    }
}
