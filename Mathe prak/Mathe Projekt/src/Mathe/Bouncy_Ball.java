package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bouncy_Ball extends Animation {

    @Override
    protected ArrayList<JFrame> createFrames(ApplicationTime applicationTimeThread) {
        // a list of all frames (windows) that will be shown
        ArrayList<JFrame> frames = new ArrayList<>();

        // Create main frame (window)
        JFrame frame = new JFrame("Mathematik und Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new Bouncy_Ball_Panel(applicationTimeThread);
        frame.add(panel);
        frame.pack(); // adjusts size of the JFrame to fit the size of it's components
        frame.setVisible(true);

        frames.add(frame);
        return frames;
    }

}

class Bouncy_Ball_Panel extends JPanel {

    // panel has a single time tracking thread associated with it
    private final ApplicationTime t;

    private double time;

    public Bouncy_Ball_Panel(ApplicationTime thread) {
        this.t = thread;
    }

    // set this panel's preferred size for auto-sizing the container JFrame
    public Dimension getPreferredSize() {
        return new Dimension(Konstanten.WINDOW_WIDTH, Konstanten.WINDOW_HEIGHT);
    }

    int width = Konstanten.WINDOW_WIDTH;
    int height = Konstanten.WINDOW_HEIGHT;
    double startX = 20;
    double startY = 20;
    double vX = 160 * 2;
    double vY = 20 * 2;
    double currentX = startX;
    double currentY = startY;
    int diameter = 50;
    private double lastFrameTime = 0.0;

    // drawing operations should be done in this method
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        time = t.getTimeInSeconds();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);
        double deltaTime = time - lastFrameTime;
        lastFrameTime = time;

        currentX = currentX + (vX * deltaTime);
        currentY = currentY + (vY * deltaTime);

        // bounce might change x coordinate

        g.setColor(Color.RED);
        g.fillOval((int) currentX, (int) currentY, diameter, diameter);

        if (currentX >= width - diameter) {
            // Object has hit the right-hand wall
            vX = -vX;
            currentX = currentX - 1;

        } else if (currentX <= 0) { // else if to prevent double-checking hence saving performance
            // Object has hit the left-hand wall
            vX = -vX;
            currentX = currentX + 1;
        }

        if (currentY >= height-diameter) {
            // Object has hit the floor
            vY = -vY;
            currentY = currentY - 1;

        } else if (currentY <= 0) {
            // Object has hit the ceiling
            vY = -vY;
            currentY = currentY + 1;
        }
    }
}