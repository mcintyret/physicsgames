package com.mcintyret.physicsgames;

import javax.swing.*;

public abstract class GamePanel extends JPanel {

    public GamePanel() {
        setOpaque(true);
        setFocusable(true);
        requestFocus();
    }

    public abstract Response millisPassed(long millis);

}
