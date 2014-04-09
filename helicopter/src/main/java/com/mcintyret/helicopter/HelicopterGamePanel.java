package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class HelicopterGamePanel extends GamePanel {

    private static final String SCORE_TEXT = "Score: ";

    private static final Color HELICOPTER_COLOR = Color.RED;
    private static final Color BARRIER_COLOR = Color.GREEN;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private final HelicopterController controller = new HelicopterController(600, 600, 20, 10);

    private volatile boolean thrusting = false;

    private volatile boolean live = true;

    private long score = 0;
    private final JLabel scoreLabel = new JLabel(SCORE_TEXT);

    public HelicopterGamePanel() {
        setLayout(new BorderLayout());
        add(scoreLabel, BorderLayout.SOUTH);
        add(new JPanel() {
            {
                setBackground(BACKGROUND_COLOR);
                setSize(new Dimension(600, 600));
            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);

                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(HELICOPTER_COLOR);

                Rectangles.fill(controller.getHelicopter(), g2);

                g2.setColor(BARRIER_COLOR);

                for (Barrier b : controller.getBarriers()) {
                    Rectangles.fill(b, g2);
                }

            }

        }, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    thrusting = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    thrusting = false;
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                thrusting = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                thrusting = false;
            }
        });
    }

    @Override
    public Response millisPassed(long millis) {
        Response resp = NoOpResponse.INSTANCE;
        if (live) {
            score += millis;
            scoreLabel.setText(SCORE_TEXT + score);
            if (controller.updatePosition(millis, thrusting)) {
                live = false;
                resp = diedResonse;
            }
        }
        return resp;
    }

    final Response diedResonse = new Response() {
        @Override
        public void doResponse(GUI gui) {
            gui.pause();
            JOptionPane.showMessageDialog(HelicopterGamePanel.this, "SHIT, you died!", "LOSER", JOptionPane.ERROR_MESSAGE);
            gui.reset();
        }
    };

}
