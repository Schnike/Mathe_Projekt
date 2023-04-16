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
        controlFrame.setLayout(new GridLayout(2, 2, 10, 0)); // manages the layout of panels in the frame

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

            JLabel el1 = new JLabel("Adjust elastic scaling red Ball:");
            JLabel el1_timeScalingLabel = new JLabel("Current scaling :");
            JLabel currentScaling_el1 = new JLabel("0.5");

            JScrollBar scrollBar_el1 = new JScrollBar(Adjustable.HORIZONTAL, 1/10, 5/10, 0, 1);
            scrollBar_el1.addAdjustmentListener(E1 -> {
                double newScaling_el1 = (double) scrollBar_el1.getValue() / 5/10;
                thread.changeTimeScaling(newScaling_el1);
                currentScaling_el1.setText(Double.toString(newScaling_el1));});

                JLabel el2 = new JLabel("Adjust elastic scaling blue Ball:");
                JLabel el2_timeScalingLabel = new JLabel("Current scaling :");
                JLabel currentScaling_el2 = new JLabel("0.5");

                JScrollBar scrollBar_el2 = new JScrollBar(Adjustable.HORIZONTAL, 1/10, 5/10, 0, 1);
                scrollBar_el2.addAdjustmentListener(E2 -> {
                    double newScaling_el2 = (double) scrollBar_el2.getValue() / 5/10;
                    thread.changeTimeScaling(newScaling_el2);
                    currentScaling_el2.setText(Double.toString(newScaling_el2));
        });
        scrollPanel.add(el1);
        scrollPanel.add(scrollBar_el1);

        scrollPanel.add(el1_timeScalingLabel);
        scrollPanel.add(currentScaling_el1);
        controlFrame.pack();

        scrollPanel.add(el2);
        scrollPanel.add(scrollBar_el2);

        scrollPanel.add(el2_timeScalingLabel);
        scrollPanel.add(currentScaling_el2);
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
        double startX = 20; //Roter Ball
        double startY = 20;
        double vX = 100*5;
        double vY = 100*5;
        double currentX = startX;
        double currentY = startY;
        int diameter = 50;
        private double lastFrameTime = 0.0;
        double startX_2 = width-20; //Blauer Ball
        double startY_2 = 20;
        double vX_2 = -100*5;
        double vY_2 = 100*5;
        double currentX_2 = startX_2;
        double currentY_2 = startY_2;
        int diameter_2 = 50;
        private double lastFrameTime_2 = 0.0;



        // drawing operations should be done in this method
        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2d;
            g2d = (Graphics2D) g;

            int originX = 0;
            int originY = 0;
            super.paintComponent(g);
            time = t.getTimeInSeconds();

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, width, height);

            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            double deltaTime_2 = time - lastFrameTime_2;
            lastFrameTime_2 = time;

            currentX = currentX + (vX * deltaTime);
            currentY = currentY + (vY * deltaTime);
            currentX_2 = currentX_2 + (vX_2 * deltaTime_2);
            currentY_2 = currentY_2 + (vY_2 * deltaTime_2);

            // bounce might change x coordinate

            g2d.setStroke(new BasicStroke(5.0f)); //line width

            g.setColor(Color.BLACK);
            g.drawLine(originX + 100, originY, originX, originY + 100); //band oben links

            g.setColor(Color.BLACK);
            g.drawLine(originX + 100, originY+height, originX, originY + height - 100); //band unten links

            g.setColor(Color.BLACK);
            g.drawLine(originX + width - 100, originY, originX + width, originY + 100); //band oben rechts

            g.setColor(Color.BLACK);
            g.drawLine(originX + +width - 100, originY+height, originX + width, originY + height - 100); //band unten rechts

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

            g.setColor(Color.BLUE);
            g.fillOval((int) currentX_2, (int) currentY_2, diameter_2, diameter_2);

            if (currentX_2 >= width - diameter_2) {
                // Object has hiyt the right-hand wall
                vX_2 = -vX_2;
                currentX_2 = currentX_2 - 1;

            } else if (currentX_2 <= 0) { // else if to prevent double-checking hence saving performance
                // Object has hit the left-hand wall
                vX_2 = -vX_2;
                currentX_2 = currentX_2 + 1;
            }


            if (currentY_2 >= height - diameter_2) {
                // Object has hit the floor
                vY_2 = -vY_2;
                currentY_2 = currentY_2 - 1;

            } else if (currentY_2 <= 0) {
                // Object has hit the ceiling
                vY_2 = -vY_2;
                currentY_2 = currentY_2 + 1;
            }
        }
    }
}
