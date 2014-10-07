/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import static whackamole.EngineCore.MOLES;

/**
 *
 * @author Expiscor
 */
public class RenderCore {

    public static int SCREEN_WIDTH = 540;
    public static int SCREEN_HEIGHT = 540;

    public static final Group GROUP_ROOT = new Group();

    public static final Scene SCENE_SURFACE = new Scene(GROUP_ROOT, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
    private static final Canvas CANVAS_SURFACE = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);

    private static void initSurfaces() {
        GROUP_ROOT.getChildren().add(CANVAS_SURFACE);
    }

    public static void prepRenderCore() {
        initSurfaces();
    }

    public static void renderAll() {

        renderField();
        renderMoles();

    }

    private static void renderMoles()
    {
        
        for (MoleObject m : MOLES)
        {CANVAS_SURFACE.getGraphicsContext2D().setFill(m.getColor().interpolate( Color.TRANSPARENT, m.getFade()));
            CANVAS_SURFACE.getGraphicsContext2D().fillOval(m.linkedSlotPosition.getX() - 30, m.linkedSlotPosition.getY() - 30, 60, 60);
        }
    }
    
    private static void renderField() {
        CANVAS_SURFACE.getGraphicsContext2D().setFill(Color.WHITE);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                CANVAS_SURFACE.getGraphicsContext2D().fillRect(x * 180, y * 180, 180, 180);

            }
        }

        CANVAS_SURFACE.getGraphicsContext2D().setStroke(Color.BLUE);
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                CANVAS_SURFACE.getGraphicsContext2D().strokeRect(x * 180, y * 180, 180, 180);

            }
        }
    }

}
