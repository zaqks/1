package com.zaqksdev.el_meyloud_RE.services.coords;

import java.lang.Math;

public class Coords {

    private Point p1, p2;

    public Coords(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public float getDistance() {
        // hna its an arc length
        // 6371 is the earth radius
        return (float) (Math.acos(
                Math.sin(p1.x) * Math.sin(p2.x) +
                        Math.cos(p1.x) * Math.cos(p2.x) *
                                Math.cos(p2.y - p1.y))
                * 6371);
    }

}


