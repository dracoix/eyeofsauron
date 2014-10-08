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
import static whackamole.EngineCore.GRID;
import static whackamole.EngineCore.MOLES;
import static whackamole.Whackamole.MOVE_X;
import static whackamole.Whackamole.MOVE_Y;

/**
 *
 * @author Expiscor
 */
public class RenderCore {

        public static double SCREEN_WIDTH = 1366;
                public static double SCREEN_HEIGHT =768;
    
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
        renderHover();

    }
    
    private static void renderHover()
    {
        CANVAS_SURFACE.getGraphicsContext2D().setFill(Color.BLACK.interpolate(Color.TRANSPARENT, 0.8));
        CANVAS_SURFACE.getGraphicsContext2D().fillOval(MOVE_X - 100, MOVE_Y -100, 200, 200);
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
        for (int x = 0; x < GRID; x++) {
            for (int y = 0; y < GRID; y++) {
                CANVAS_SURFACE.getGraphicsContext2D().fillRect(x * SCREEN_WIDTH/GRID, y * SCREEN_HEIGHT/GRID, SCREEN_WIDTH/GRID, SCREEN_HEIGHT/GRID);

            }
        }

        CANVAS_SURFACE.getGraphicsContext2D().setStroke(Color.BLUE);
        for (int x = 0; x < GRID; x++) {
            for (int y = 0; y < GRID; y++) {
                CANVAS_SURFACE.getGraphicsContext2D().strokeRect(x * SCREEN_WIDTH/GRID, y * SCREEN_HEIGHT/GRID, SCREEN_WIDTH/GRID, SCREEN_HEIGHT/GRID);

            }
        }
    }

}
