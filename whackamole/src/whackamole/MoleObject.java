/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import javafx.scene.paint.Color;
import static whackamole.EngineCore.FILLED_SLOTS;
import static whackamole.Whackamole.MOVE_X;
import static whackamole.Whackamole.MOVE_Y;

/**
 *
 * @author Expiscor
 */
public class MoleObject {

    public static final long DIG_AFTER_BOP = 60;
    public static final long DIG_AFTER_HIDE = 150;

    boolean garbage;

    int slot;
    BetterPoint2D linkedSlotPosition;

    Color myColor = Color.BLUE;

    boolean bopped;
    long timetolive = DIG_AFTER_BOP;
    long timetohide = DIG_AFTER_HIDE;
    double fade;
    double alpha;

    public MoleObject(int slot, BetterPoint2D linkedSlotPosition) {
        this.slot = slot;
        this.linkedSlotPosition = linkedSlotPosition;
    }

    public void bop() {
        bopped = true;
        myColor = Color.RED;
    }

    public boolean collided() {
        return linkedSlotPosition.distance(MOVE_X, MOVE_Y) < 40;
    }

    public double getFade() {
        if (bopped) {
            return 1 - fade;
        }
        alpha = Math.sin(Math.log1p(fade* Math.E) * fade  * Math.PI);
        if (alpha < 0) return 1;
        return 1- alpha;

    }

    public void tick() {

        if (garbage) {
            return;
        }

        if (bopped == true) {
            fade = (double) timetolive / DIG_AFTER_BOP;
            timetolive--;

        } else {
            fade = (double) timetohide / DIG_AFTER_HIDE;
            timetohide--;
        }

        if ((timetolive < 0) || (timetohide < 0)) {
            destroy();
        }

    }

    public Color getColor() {
        return myColor;
    }

    public void destroy() {
        FILLED_SLOTS[slot] = false;
        this.garbage = true;
    }

    public boolean garbage() {
        return garbage;
    }
}
