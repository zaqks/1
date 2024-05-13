package com.zaqksdev.el_meyloud_RE.services.coords;

import com.zaqksdev.el_meyloud_RE.models.Agent;
import com.zaqksdev.el_meyloud_RE.models.Client;
import com.zaqksdev.el_meyloud_RE.models.Property;

public class Point {
    public float x, y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }

    public Point getPoint(Property obj) {
        return new Point(obj.getX(), obj.getY());
    }

    public Point getPoint(Agent obj) {
        return new Point(obj.getX(), obj.getY());
    }

    public Point getPoint(Client obj) {
        return new Point(obj.getX(), obj.getY());
    }
}