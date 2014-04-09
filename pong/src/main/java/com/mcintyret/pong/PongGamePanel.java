package com.mcintyret.pong;

import com.mcintyret.physicsgames.GamePanel;
import com.mcintyret.physicsgames.NoOpResponse;
import com.mcintyret.physicsgames.Rectangles;
import com.mcintyret.physicsgames.Response;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * User: tommcintyre
 * Date: 4/8/14
 */
public class PongGamePanel extends GamePanel {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    private static final Color BALL_COLOR = Color.WHITE;

    private static final Color PADDLE_COLOR = Color.WHITE;

    private final PongController pongController = new PongController();

    private Motion leftMotion = Motion.NONE;

    private Motion rightMotion = Motion.NONE;

    private int leftScore = 0;

    private int rightScore = 0;

    private final JLabel scoreLabel = new JLabel();

    public PongGamePanel() {
        setLayout(new BorderLayout());
        add(scoreLabel, BorderLayout.NORTH);
        setScoreLabelText();
        add(new JPanel() {
            {
                setBackground(BACKGROUND_COLOR);
            }

            @Override
            public void paint(Graphics g) {
                super.paint(g);

                Graphics2D g2 = (Graphics2D) g;

                g2.setColor(BALL_COLOR);
                Ball ball = pongController.getBall();
                g2.fillOval(ball.getxPos(), ball.getyPos(), ball.getDiameter(), ball.getDiameter());

                g2.setColor(PADDLE_COLOR);

                Rectangles.fill(pongController.getLeftPaddle(), g2);
                Rectangles.fill(pongController.getRightPaddle(), g2);

            }
        }, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        leftMotion = Motion.UP;
                        break;
                    case KeyEvent.VK_S:
                        leftMotion = Motion.DOWN;
                        break;
                    case KeyEvent.VK_UP:
                        rightMotion = Motion.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        rightMotion = Motion.DOWN;
                        break;
                    default:
                        return;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                    case KeyEvent.VK_S:
                        leftMotion = Motion.NONE;
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        rightMotion = Motion.NONE;
                        break;
                }
            }
        });
    }

    private void setScoreLabelText() {
        scoreLabel.setText("LEFT: " + leftScore + "         RIGHT: " + rightScore);
    }

    private void onScore() {
        setScoreLabelText();
        pongController.resetPositions();
    }

    @Override
    public Response millisPassed(long millis) {
        switch (pongController.updatePosition(millis, leftMotion, rightMotion)) {
            case LEFT_SCORES:
                leftScore++;
                onScore();
                break;
            case RIGHT_SCORES:
                rightScore++;
                onScore();
                break;
        }
        return NoOpResponse.INSTANCE;
    }

}
