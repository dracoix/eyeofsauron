/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

/**
 *
 * @author Expiscor
 */
public class BetterPoint2D {

    double x;
    double y;

    BetterPoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(BetterPoint2D p) {
        return distance(p.x, p.y);
    }

    public double distance(double x, double y) {
        return Math.sqrt(sqr(this.x - x) + sqr(this.y - y));
    }

    private double sqr(double a) {
        return a * a;
    }
}
