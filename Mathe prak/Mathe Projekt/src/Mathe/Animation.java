package Mathe;

import utils.ApplicationTime;
import utils.FrameUpdater;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;


public abstract class Animation {

    public void start() {
        ApplicationTime applicationTimeThread = new ApplicationTime();
        applicationTimeThread.start();
        FrameUpdater frameUpdater = new FrameUpdater(createFrames(applicationTimeThread));
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(frameUpdater, 100, Konstanten.TPF);

    };

    protected abstract ArrayList<JFrame> createFrames(ApplicationTime applicationTimeThread);
}
