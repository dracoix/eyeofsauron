/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Expiscor
 */
public class Whackamole extends Application {

    private static double WINDOW_Y, WINDOW_X;
    public static double MOVE_Y, MOVE_X;
    public static boolean MOUSE_DOWN;
    
    private static EngineDriver ENGINE = new EngineDriver();

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(true);
        
        primaryStage.addEventHandler(MouseEvent.MOUSE_MOVED, (MouseEvent t) -> {
            MOVE_X = t.getSceneX();
            MOVE_Y = t.getSceneY();
        });
        primaryStage.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent t) -> {
            WINDOW_X = t.getSceneX();
            WINDOW_Y = t.getSceneY();
            MOUSE_DOWN = true;
        });
        
         primaryStage.addEventHandler(MouseEvent.MOUSE_RELEASED, (MouseEvent t) -> {
            MOUSE_DOWN = false;
        });
        primaryStage.addEventHandler(MouseEvent.MOUSE_DRAGGED, (MouseEvent t) -> {
            //primaryStage.setX(t.getScreenX() - WINDOW_X);
            //primaryStage.setY(t.getScreenY() - WINDOW_Y);
        });
        ENGINE.init(primaryStage);
        ENGINE.fire();
        primaryStage.show();
    }
    
}
