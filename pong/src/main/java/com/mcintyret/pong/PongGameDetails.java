package com.mcintyret.pong;

import com.mcintyret.physicsgames.GameDetails;
import com.mcintyret.physicsgames.GamePanel;

/**
 * User: tommcintyre
 * Date: 4/8/14
 */
public enum PongGameDetails implements GameDetails {
    INSTANCE;

    @Override
    public GamePanel newGamePanel() {
        return new PongGamePanel();
    }

    @Override
    public String getGameName() {
        return "Pong";
    }
}
