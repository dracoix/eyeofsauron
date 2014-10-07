/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import static whackamole.BetterRandom.rnd;
import static whackamole.Whackamole.MOUSE_DOWN;

/**
 *
 * @author Expiscor
 */
public class EngineCore {

    public static final int MASTER_FPS = 30;
    public static final int MASTER_FRAME_TIME = 1000 / MASTER_FPS;

    public static final ArrayList<MoleObject> MOLES = new ArrayList<>();

    public static boolean[] FILLED_SLOTS = new boolean[9];
    public static BetterPoint2D[] SLOT_POSITIONS = new BetterPoint2D[9];

    public static void prepEngineCore() {
        int x;
        int y;

        SLOT_POSITIONS[0] = new BetterPoint2D(90, 90);
        SLOT_POSITIONS[1] = new BetterPoint2D(270, 90);
        SLOT_POSITIONS[2] = new BetterPoint2D(450, 90);

        SLOT_POSITIONS[3] = new BetterPoint2D(90, 270);
        SLOT_POSITIONS[4] = new BetterPoint2D(270, 270);
        SLOT_POSITIONS[5] = new BetterPoint2D(450, 270);

        SLOT_POSITIONS[6] = new BetterPoint2D(90, 450);
        SLOT_POSITIONS[7] = new BetterPoint2D(270, 450);
        SLOT_POSITIONS[8] = new BetterPoint2D(450, 450);
    }

    public static void ADD_NEW_MOLE() {
        if (MOLES.size() >= 9) {
            return;
        }
        boolean unfilled = true;
        int try_slot;
        while (unfilled) {
            try_slot = (int) Math.abs(rnd() % 9);
            if (FILLED_SLOTS[try_slot] == false) {
                FILLED_SLOTS[try_slot] = true;
                unfilled = false;
                MOLES.add(new MoleObject(try_slot, SLOT_POSITIONS[try_slot]));
            }
        }
    }

    public static void engineTick() {
        Iterator<MoleObject> itr = MOLES.iterator();
        MoleObject m;
        while (itr.hasNext()) {
            m = itr.next();
            m.tick();
            if (m.collided()) {
                if (MOUSE_DOWN) {
                    m.bop();
                }
            }
            if (m.garbage()) {
                itr.remove();
            }
        }
    }

}
