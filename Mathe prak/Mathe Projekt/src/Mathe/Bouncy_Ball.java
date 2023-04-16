package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bouncy_Ball extends Animation {
    static JButton buttonContinue = new JButton();
    static JButton buttonStop = new JButton();
    static JButton buttonPause = new JButton();


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
        createControlFrame(applicationTimeThread);
        return frames;
    }

    private static void createControlFrame(ApplicationTime thread) {
        // Create a new frame
        JFrame controlFrame = new JFrame("Mathematik und Simulation");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setLayout(new GridLayout(1, 2, 10, 0)); // manages the layout of panels in the frame

        // Add a JPanel as the new drawing surface
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4, 5, 0)); // manages the layout of elements in the panel (buttons, labels,
        // other panels, etc.)
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new GridLayout(2, 2, 5, 5));
        controlFrame.add(panel);
        controlFrame.add(scrollPanel);
        controlFrame.setVisible(true);

        // set up first Panel
        buttonContinue = new JButton();
        buttonContinue.setBackground(Color.WHITE);
        buttonContinue.addActionListener(new Control_Panel(buttonContinue, controlFrame, thread));
        buttonContinue.setText("Continue");

        buttonStop = new JButton();
        buttonStop.setBackground(Color.WHITE);
        buttonStop.addActionListener(new Control_Panel(buttonStop, controlFrame, thread));
        buttonStop.setText("Stop (forever)");

        buttonPause = new JButton();
        buttonPause.setBackground(Color.WHITE);
        buttonPause.addActionListener(new Control_Panel(buttonPause, controlFrame, thread));
        buttonPause.setText("Pause");

        panel.add(buttonContinue);
        panel.add(buttonStop);
        panel.add(buttonPause);

        // set up second panel
        JLabel scrollLabel = new JLabel("Adjust time scaling:");
        JLabel timeScalingLabel = new JLabel("Current scaling :");
        JLabel currentScaling = new JLabel("1");

        JScrollBar scrollBar = new JScrollBar(Adjustable.HORIZONTAL, 1, 5, -50, 55);
        scrollBar.addAdjustmentListener(e -> {
            double newScaling = (double) scrollBar.getValue() / 5;
            thread.changeTimeScaling(newScaling);
            currentScaling.setText(Double.toString(newScaling));
        });
        scrollPanel.add(scrollLabel);
        scrollPanel.add(scrollBar);

        scrollPanel.add(timeScalingLabel);
        scrollPanel.add(currentScaling);
        controlFrame.pack();


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

            if (currentY >= height - diameter) {
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
}