package com.mcintyret.pong;

import com.mcintyret.physicsgames.GUI;

/**
 * User: tommcintyre
 * Date: 4/8/14
 */
public class PongRunner {

    public static void main(String[] args) {
        new GUI(PongGameDetails.INSTANCE).start();
    }

}
