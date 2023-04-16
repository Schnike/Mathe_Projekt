package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control_Panel implements ActionListener {
    JButton button;
    JFrame frame;
    ApplicationTime applicationTimeThread;

    public Control_Panel(JButton button, JFrame frame, ApplicationTime applicationTimeThread) {
        this.button = button;
        this.frame = frame;
        this.applicationTimeThread = applicationTimeThread;
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.equals(Bouncy_Ball.buttonPause)) {
            applicationTimeThread.pauseTime();
            System.out.println("Pause pressed");
        } else if (button.equals(Bouncy_Ball.buttonStop)) {
            applicationTimeThread.endThread();
            System.out.println("Stop pressed, thread ended");
        } else if (button.equals(Bouncy_Ball.buttonContinue)) {
            applicationTimeThread.continueTime();
            System.out.println("Continue pressed");
        }
    }
}