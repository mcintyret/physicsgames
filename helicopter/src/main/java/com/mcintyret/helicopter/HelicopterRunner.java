package com.mcintyret.helicopter;

import com.mcintyret.physicsgames.GUI;

public class HelicopterRunner {

    public static void main(String[] args) throws InterruptedException {
        new GUI(HelicopterGameDetails.INSTANCE).start();
    }

}
