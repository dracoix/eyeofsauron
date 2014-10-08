/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import java.util.ArrayList;
import java.util.Iterator;
import static whackamole.BetterRandom.rnd;
import static whackamole.RenderCore.SCREEN_HEIGHT;
import static whackamole.RenderCore.SCREEN_WIDTH;
import static whackamole.Whackamole.MOUSE_DOWN;

/**
 *
 * @author Expiscor
 */
public class EngineCore {

    public static final int MASTER_FPS = 30;
    public static final int MASTER_FRAME_TIME = 1000 / MASTER_FPS;

    public static int GRID = 4;

    public static final ArrayList<MoleObject> MOLES = new ArrayList<>();

    public static boolean[] FILLED_SLOTS = new boolean[GRID * GRID];
    public static BetterPoint2D[] SLOT_POSITIONS = new BetterPoint2D[GRID * GRID];

    public static void prepEngineCore() {
        double x = SCREEN_WIDTH / (GRID * 2);
        double y = SCREEN_HEIGHT / (GRID * 2);
        int i = 0;

        for (int j = 0; j < GRID; j++) {
            for (int k = 0; k < GRID; k++) {
                SLOT_POSITIONS[i] = new BetterPoint2D((k * x * 2) + x, (j * y * 2) + y);
                i++;
            }
        }

//        SLOT_POSITIONS[0] = new BetterPoint2D(x, y);
//        SLOT_POSITIONS[1] = new BetterPoint2D(x * 3, y);
//        SLOT_POSITIONS[2] = new BetterPoint2D(x * 5, y);
//
//        SLOT_POSITIONS[3] = new BetterPoint2D(x, y * 3);
//        SLOT_POSITIONS[4] = new BetterPoint2D(x * 3, y * 3);
//        SLOT_POSITIONS[5] = new BetterPoint2D(x * 5, y * 3);
//
//        SLOT_POSITIONS[6] = new BetterPoint2D(x, y * 5);
//        SLOT_POSITIONS[7] = new BetterPoint2D(x * 3, y * 5);
//        SLOT_POSITIONS[8] = new BetterPoint2D(x * 5, y * 5);
    }

    public static void ADD_NEW_MOLE() {
        if (MOLES.size() >= GRID * GRID) {
            return;
        }
        boolean unfilled = true;
        int try_slot;
        while (unfilled) {
            try_slot = (int) Math.abs(rnd() % (GRID * GRID));
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
