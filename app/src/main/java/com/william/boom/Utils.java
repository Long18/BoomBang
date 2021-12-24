package com.william.boom;

public class Utils {

    /**
     * getDistanceBP = get Distance Betwwen Points, retuns the distance between 2d points p1 and p2
     *
     * @param p1x Position X one
     * @param p1y Position Y one
     * @param p2x Position X two
     * @param p2y Position Y two
     * @return
     */
    public static double getDistanceBP(double p1x, double p1y, double p2x, double p2y) {
        return Math.sqrt(
                Math.pow(p1x - p2x, 2) +
                        Math.pow(p1y - p2y, 2)
        );
    }
}
