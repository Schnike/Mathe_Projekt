package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bouncy_Ball extends Animation {
    static JButton buttonContinue = new JButton();
    static JButton buttonStart = new JButton();
    static JButton buttonPause = new JButton();
    static JButton buttonReset = new JButton();
    private static double eR=1;
    public static double eB=1;


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
        buttonStart = new JButton();
        buttonStart.setBackground(Color.WHITE);
        buttonStart.addActionListener(new Control_Panel(buttonStart, controlFrame, thread));
        buttonStart.setText("Start");

        buttonContinue = new JButton();
        buttonContinue.setBackground(Color.WHITE);
        buttonContinue.addActionListener(new Control_Panel(buttonContinue, controlFrame, thread));
        buttonContinue.setText("Continue");

        buttonPause = new JButton();
        buttonPause.setBackground(Color.WHITE);
        buttonPause.addActionListener(new Control_Panel(buttonPause, controlFrame, thread));
        buttonPause.setText("Pause");

        buttonReset = new JButton();
        buttonReset.setBackground(Color.WHITE);
        buttonReset.addActionListener(new Control_Panel(buttonReset, controlFrame, thread));
        buttonReset.setText("Reset");

        panel.add(buttonStart);
        panel.add(buttonReset);
        panel.add(buttonPause);
        panel.add(buttonContinue);

        // set up second panel
        //ScrollBar für Roten Ball
            JLabel el1 = new JLabel("Adjust elastic scaling blue Ball:");
            JLabel el1_elasticScalingLabel = new JLabel("Current scaling :");
            JLabel currentScaling_el1 = new JLabel("1.0");

            JScrollBar scrollBar_el1 = new JScrollBar(Adjustable.HORIZONTAL, 100, 10, 0, 110);
            scrollBar_el1.addAdjustmentListener(E1 -> {
                double newScaling_el1 = (double) scrollBar_el1.getValue() / 100;
                eR=newScaling_el1;
                currentScaling_el1.setText(Double.toString(newScaling_el1));
            });
                //ScrollBar für Blauen Ball
                JLabel el2 = new JLabel("Adjust elastic scaling red Ball:");
                JLabel el2_elasticScalingLabel = new JLabel("Current scaling :");
                JLabel currentScaling_el2 = new JLabel("1.0");

                JScrollBar scrollBar_el2 = new JScrollBar(Adjustable.HORIZONTAL, 100, 10, 0, 110);
                scrollBar_el2.addAdjustmentListener(E2 -> {
                    double newScaling_el2 = (double) scrollBar_el2.getValue() / 100;
                    eB=newScaling_el2;
                    currentScaling_el2.setText(Double.toString(newScaling_el2));
        });
        scrollPanel.add(el1);
        scrollPanel.add(scrollBar_el1);

        scrollPanel.add(el1_elasticScalingLabel);
        scrollPanel.add(currentScaling_el1);
        controlFrame.pack();

        scrollPanel.add(el2);
        scrollPanel.add(scrollBar_el2);

        scrollPanel.add(el2_elasticScalingLabel);
        scrollPanel.add(currentScaling_el2);
        controlFrame.pack();
    }

    class Bouncy_Ball_Panel extends JPanel {

        // panel has a single time tracking thread associated with it
        private final ApplicationTime t;
        private double time;
        private final double currentX_1=50;
        private final double currentX_2=Konstanten.WINDOW_WIDTH-100;
        private final double currentY_1=Konstanten.WINDOW_HEIGHT/2;
        private final double currentY_2=Konstanten.WINDOW_HEIGHT/2;
        private final double vX=200;
        private final double vY=200;
        private final double diameter=50;
        private Ball k1;
        private Ball k2;
        private Ball s1;
        private Ball s2;

        public Bouncy_Ball_Panel(ApplicationTime thread) {
            k1 = new Ball(currentX_1, currentY_1, diameter, vX, -vY); //blue
            k2 = new Ball(currentX_2, currentY_2, diameter, -vX, -vY);//red
            s1 = new Ball(k1);
            s2 = new Ball(k2);
            System.out.println(s1.vX);
            System.out.println("Test");
            this.t = thread;
        }


        // set this panel's preferred size for auto-sizing the container JFrame
        public Dimension getPreferredSize() {
            return new Dimension(Konstanten.WINDOW_WIDTH, Konstanten.WINDOW_HEIGHT);
        }

        int width = Konstanten.WINDOW_WIDTH;
        int height = Konstanten.WINDOW_HEIGHT;
        private double lastFrameTime = 0.0;


        // drawing operations should be done in this method
        @Override
        protected void paintComponent(Graphics g) {

                Graphics2D g2d;
                g2d = (Graphics2D) g;

                int originX = 0;
                int originY = 0;
                super.paintComponent(g);

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(0, 0, width, height);

                time = t.getTimeInSeconds();

                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                // bounce might change x coordinate

                if(Control_Panel.Reset==true){
                    Control_Panel.Reset=false;
                    k1=new Ball(s1);
                    k2=new Ball(s2);
                }
                k1.draw(g,new Color(0f,0f,1f,0.5f));
                k2.draw(g,new Color(1f,0f,0f,0.5f));
                if(true==Control_Panel.Start){
                    k1.moveInArea(deltaTime,width,height,eR);
                    k2.moveInArea(deltaTime,width,height,eB);
                    if(Physik.ball_treffen(currentX_1,currentY_1,50, currentX_2,currentY_2,50) == true){
                        k1.vX = -k1.vX;
                    }
                }

                g2d.setStroke(new BasicStroke(5.0f)); //line width

                g.setColor(Color.BLACK);
                g.drawLine(originX + 150, originY, originX, originY + 150); //band oben links

                g.setColor(Color.BLACK);
                g.drawLine(originX + 150, originY + height, originX, originY + height - 150); //band unten links

                g.setColor(Color.BLACK);
                g.drawLine(originX + width - 150, originY, originX + width, originY + 150); //band oben rechts

                g.setColor(Color.BLACK);
                g.drawLine(originX + width - 150, originY + height, originX + width, originY + height - 150); //band unten rechts
        }
    }
}
