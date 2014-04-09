package com.mcintyret.physicsgames;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.mcintyret.physicsgames.Uninterruptibles.sleepUninterruptibly;
import static com.mcintyret.physicsgames.Uninterruptibles.waitUninterruptibly;

public class GUI {

    private static final long FPS = 30L;

    private static final long RENDER_TIME = 1000L / FPS;

    private volatile boolean paused;

    private final Object pauseObj = new Object();

    private final GameDetails gameDetails;

    private final JFrame frame;

    private final AtomicBoolean reset = new AtomicBoolean(true);

    public GUI(GameDetails gameDetails) {
        this.gameDetails = gameDetails;

        frame = new JFrame(gameDetails.getGameName());
        frame.setSize(new Dimension(600, 640));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setVisible(true);
            }
        });
    }

    public void start() {
        GamePanel panel = null;
        while (true) {
            if (reset.getAndSet(false)) {
                panel = gameDetails.newGamePanel();
                frame.setContentPane(panel);
            }

            if (paused) {
                synchronized (pauseObj) {
                    waitUninterruptibly(pauseObj);
                }
            }

            long now = System.currentTimeMillis();
            long nextRender = now + RENDER_TIME;

            final long diff = nextRender - now;

            if (diff > 0) {
                sleepUninterruptibly(diff, TimeUnit.MILLISECONDS);
            }

            final GamePanel finGp = panel;
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    finGp.millisPassed(diff).doResponse(GUI.this);
                    finGp.repaint();
                }
            });
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
        synchronized (pauseObj) {
            pauseObj.notify();
        }
    }

    public void reset() {
        reset.set(true);
        unpause();
    }

}
