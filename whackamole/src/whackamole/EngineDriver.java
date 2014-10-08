/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whackamole;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import static whackamole.BetterRandom.rnd;
import static whackamole.EngineCore.ADD_NEW_MOLE;
import static whackamole.EngineCore.MASTER_FRAME_TIME;
import static whackamole.EngineCore.engineTick;
import static whackamole.EngineCore.prepEngineCore;
import static whackamole.RenderCore.*;

/**
 *
 * @author Expiscor
 */
public class EngineDriver {

    private static Timeline MAINLOOP;
    

    public EngineDriver() {
        //GraphicsDevice myDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //SCREEN_WIDTH = myDevice.getDisplayMode().getWidth();
        //SCREEN_HEIGHT = myDevice.getDisplayMode().getHeight();
        //Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //SCREEN_WIDTH = primaryScreenBounds.getWidth();
        //SCREEN_HEIGHT = primaryScreenBounds.getHeight();
        prepEngineCore();
        prepRenderCore();

    }

    void init(Stage primaryStage) {

        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);
        primaryStage.setScene(SCENE_SURFACE);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(EngineDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

        final KeyFrame oneFrame = new KeyFrame(Duration.millis(MASTER_FRAME_TIME), (ActionEvent event) -> {
            tick();
        });

        MAINLOOP = TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build();
    }

    public void tick() {
        if (((double) (rnd() % 65536) / 65536) > 0.95) {
            ADD_NEW_MOLE();
        }
        engineTick();
        renderAll();
    }

    public void fire() {
        MAINLOOP.play();
    }
}
