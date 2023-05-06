package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control_Panel implements ActionListener {
    public static boolean Start=false;
    public static boolean Reset=false;
    public static boolean exp1=false;
    public static boolean exp2=false;
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
        if(button.equals(Bouncy_Ball.buttonStart)){
            Start=true;
            System.out.println("Start pressed");
        }
        if(button.equals(Bouncy_Ball.buttonReset)){
            Reset=true;
            Start=false;
            System.out.println("Reset pressed");
        }
        if (button.equals(Bouncy_Ball.buttonPause)) {
            applicationTimeThread.pauseTime();
            System.out.println("Pause pressed");
        }
        if(button.equals(Bouncy_Ball.buttonExample1)){
            exp1=true;
            Start=false;
            System.out.println("Example1 pressed");
        }
        if(button.equals(Bouncy_Ball.buttonExample2)){
            exp2=true;
            Start=false;
            System.out.println("Example2 pressed");
        }
        else if (button.equals(Bouncy_Ball.buttonContinue)) {
            applicationTimeThread.continueTime();
            System.out.println("Continue pressed");

        }

    }

}