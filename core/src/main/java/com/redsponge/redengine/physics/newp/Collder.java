package com.redsponge.redengine.physics.newp;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Shape;

public class Collder {

    private static final int NUM_TMP_RECTS = 6;
    private static final int NUM_TMP_CIRCLES = 6;
    private static final int NUM_TMP_VECS = 6;

    private static Rectangle[] rectangles;
    private static Circle[] circles;
    private static Vector2[] vecs;

    static {
        rectangles = new Rectangle[NUM_TMP_RECTS];
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i] = new Rectangle();
        }

        circles = new Circle[NUM_TMP_CIRCLES];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle();
        }

        vecs = new Vector2[NUM_TMP_VECS];
        for (int i = 0; i < vecs.length; i++) {
            vecs[i] = new Vector2();
        }
    }

    public static boolean rectangleRectangle(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        Rectangle a = rectangles[0], b = rectangles[1], c = rectangles[2];
        a.set(x1, y1, w1, h1);
        b.set(x2, y2, w2, h2);

        return Intersector.intersectRectangles(a, b, c);
    }

    public static boolean circleCircle(int x1, int y1, int r1, int x2, int y2, int r2) {
        return Vector2.dst2(x1, y1, x2, y2) < (r1 + r2) * (r1 + r2);
    }

    public static boolean circleRectangle(int xr, int yr, int w, int h, int xc, int yc, int r) {
        Rectangle rect = rectangles[0];
        rect.set(xr, yr, w, h);

        Vector2 a = vecs[0];
        Vector2 b = vecs[1];
        Vector2 c = vecs[2];
        Vector2 d = vecs[3];

        extractPoints(rect, a, b, c, d);

        Vector2 mid = vecs[4];
        mid.set(xc, yc);
        r *= r;

        return Intersector.intersectSegmentCircle(a, b, mid, r)
            || Intersector.intersectSegmentCircle(b, c, mid, r)
            || Intersector.intersectSegmentCircle(d, c, mid, r)
            || Intersector.intersectSegmentCircle(a, d, mid, r);
    }

    public static void extractPoints(Rectangle r, Vector2 a, Vector2 b, Vector2 c, Vector2 d) {
        float x = r.x;
        float y = r.y;
        float w = r.width;
        float h = r.height;
        a.set(x, y);
        b.set(x + w, y);
        c.set(x + w, y + h);
        d.set(x, y + h);
    }

    public static boolean checkCollision(Shape2D a, Shape2D b) {
        boolean aRect = a instanceof Rectangle;
        boolean bRect = b instanceof Rectangle;

//        boolean aCircle = a instanceof Circle;
//        boolean bCircle = b instanceof Circle;

        if (aRect ^ bRect) {
            Rectangle rect;
            Circle circle;
            if (aRect) {
                rect = (Rectangle) a;
                circle = (Circle) b;
            } else {
                rect = (Rectangle) b;
                circle = (Circle) a;
            }
            return circleRectangle((int) rect.x, (int) rect.y, (int) rect.width, (int) rect.height, (int) circle.x, (int) circle.y, (int) circle.radius);
        }

        return false;
    }

}
