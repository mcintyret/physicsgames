package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.Physics;
import com.mcintyret.physicsgames.Rectangles;

import java.util.Random;

public class HelicopterController {

    private static final double THRUST_FACTOR = 2.5D;

    private static final int NUM_BARRIERS = 4;

    private static final int BARRIER_WIDTH_TO_GAP_RATIO = 5;

    private static final double MAX_BARRIER_SIZE = 0.7D;

    private static final double MIN_BARRIER_SIZE = 0.25D;

    private final Helicopter helicopter;

    private final int levelHeight;

    private final int levelWidth;

    private final int helicopterHeight;

    private final int helicopterWidth;

    private final Barrier[] barriers = new Barrier[NUM_BARRIERS];

    private final int barrierWidth;

    private final int interBarrierGap;

    private final int newBarrierXPos;

    private final Random rand = new Random();


    public HelicopterController(int levelHeight, int levelWidth, int helicopterWidth, int helicopterHeight) {
        this.levelHeight = levelHeight;
        this.levelWidth = levelWidth;
        this.helicopterWidth = helicopterWidth;
        this.helicopterHeight = helicopterHeight;
        this.helicopter = new Helicopter(helicopterWidth, helicopterHeight, (levelHeight + helicopterHeight) / 2);

        this.barrierWidth = levelWidth / ((NUM_BARRIERS - 1) * (1 + BARRIER_WIDTH_TO_GAP_RATIO));
        this.interBarrierGap = barrierWidth * BARRIER_WIDTH_TO_GAP_RATIO;

        // initial barriers
        int xPos = interBarrierGap;
        for (int i = 0; i < NUM_BARRIERS; i++) {
            barriers[i] = newBarrier(xPos);
            xPos += (interBarrierGap + barrierWidth);
        }
        newBarrierXPos = xPos - (interBarrierGap + barrierWidth);
    }

    private Barrier newBarrier(int xPos) {
        int height = (int) (levelHeight * ((rand.nextDouble() * (MAX_BARRIER_SIZE - MIN_BARRIER_SIZE)) + MIN_BARRIER_SIZE));
        return new Barrier(barrierWidth, height, xPos, rand.nextInt(levelHeight - height));
    }

    public boolean updatePosition(long millis, boolean thrust) {
        double time = millis / 1000D;
        double verticalPos = helicopter.getYpos();
        double verticalSpeed = helicopter.getVerticalSpeed();

        double acc = thrust ? THRUST_FACTOR * -Physics.GRAVITY : Physics.GRAVITY;

        helicopter.setYpos(verticalPos + ((verticalSpeed * time) + (0.5D * acc * time * time)));
        helicopter.setVerticalSpeed(verticalSpeed + (acc * time));

        double distMoved = helicopter.getHorizontalSpeed() * time;

        for (int i = 0; i < barriers.length; i++) {
            int newX = barriers[i].getXpos() - (int) distMoved;
            if (newX + barriers[i].getWidth() <= 0) {
                barriers[i] = newBarrier(newBarrierXPos);
            } else {
                barriers[i].setXpos(newX);
            }
        }

        // TODO: cache height limit
        if (helicopter.getYpos() >= levelHeight - helicopterHeight || helicopter.getYpos() <= 0) {
            return true;
        }

        for (Barrier barrier : barriers) {
            if (Rectangles.overlap(helicopter, barrier)) {
                return true;
            }
        }

        return false;
    }

    /**
     * The number of pixels from the top of the frame to the bottom of the helicopter
     * @return
     */
    public int helicopterYpos() {
        int heliPos = (int) helicopter.getYpos();
        return levelHeight - (heliPos + helicopterHeight / 2);
    }

    public int getHelicopterHeight() {
        return helicopterHeight;
    }

    public int getHelicopterWidth() {
        return helicopterWidth;
    }

    public Barrier[] getBarriers() {
        return barriers;
    }

    public Helicopter getHelicopter() {
        return helicopter;
    }
}
