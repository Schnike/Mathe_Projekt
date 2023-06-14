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
    static JButton buttonExample1= new JButton();
    static JButton buttonExample2= new JButton();
    public static double el=1;





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
        controlFrame.setLayout(new GridLayout(3, 2, 10, 0)); // manages the layout of panels in the frame

        // Add a JPanel as the new drawing surface
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 3, 5, 0)); // manages the layout of elements in the panel (buttons, labels,
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

        buttonExample1 = new JButton();
        buttonExample1.setBackground(Color.WHITE);
        buttonExample1.addActionListener(new Control_Panel(buttonExample1, controlFrame, thread));
        buttonExample1.setText("Exp1");

        buttonExample2 = new JButton();
        buttonExample2.setBackground(Color.WHITE);
        buttonExample2.addActionListener(new Control_Panel(buttonExample2, controlFrame, thread));
        buttonExample2.setText("Exp2");


        panel.add(buttonStart);
        panel.add(buttonReset);
        panel.add(buttonPause);
        panel.add(buttonContinue);
        panel.add(buttonExample1);
        panel.add(buttonExample2);

        // set up second panel
        //ScrollBar für Roten Ball
            JLabel el1 = new JLabel("Adjust elastic scaling:");
            JLabel el1_elasticScalingLabel = new JLabel("Current scaling :");
            JLabel currentScaling_el1 = new JLabel("1.0");

            JScrollBar scrollBar_el1 = new JScrollBar(Adjustable.HORIZONTAL, 100, 10, 0, 110);
            scrollBar_el1.addAdjustmentListener(E1 -> {
                double newScaling_el1 = (double) scrollBar_el1.getValue() / 100;
                el=newScaling_el1;
                currentScaling_el1.setText(Double.toString(newScaling_el1));
            });



        scrollPanel.add(el1);
        scrollPanel.add(scrollBar_el1);

        scrollPanel.add(el1_elasticScalingLabel);
        scrollPanel.add(currentScaling_el1);
        controlFrame.pack();
    }


    class Bouncy_Ball_Panel extends JPanel {

        // panel has a single time tracking thread associated with it
        private final ApplicationTime t;
        private double time;
        private static final double currentX_1=100;
        private static final double currentX_2=Konstanten.WINDOW_WIDTH-100;
        private static final double currentY_1=Konstanten.WINDOW_HEIGHT/2;
        private static final double currentY_2=Konstanten.WINDOW_HEIGHT/2;
        private static final double vX=200;
        private static final double vY=200;
        private static final double diameter=50;
        private final double offset=diameter/2;
        private static final double mass1=1;
        private static final double mass2=1;
        public static Ball k1= new Ball(currentX_1, currentY_1, diameter, vX, vY, mass1,el);
        public static Ball k2= new Ball(currentX_2, currentY_2 ,diameter, -vX, vY, mass2,el);
        private Ball s1;
        private Ball s2;
        private double ustrich[]=Physik.Ball_Collision(Physik.bvec(k1,k2),k1, k2,el);
        private int v[]= new int[4];
        private double V[]=Physik.Schwerpunkt_Geschwindigkeit(k1, k2);
        private double u[]=Physik.cms(k1, k2);
        public  int i=0;



        public Bouncy_Ball_Panel(ApplicationTime thread) {
            s1 = new Ball(k1);
            s2 = new Ball(k2);
            this.t = thread;
            v[0]=(int) k1.vX;
            v[1]=(int) k1.vY;
            v[2]=(int) k2.vX;
            v[3]=(int) k2.vY;
        }

        public String a = "bx:     [" + (int) Physik.bvec(k1, k2)[0] + "," + (int) Physik.bvec(k1, k2)[1] + "]";
        public String b = "|b|:    " + (int) Physik.dis(k1, k2);
        public String c = "u1_R:  [" + (int) u[0] + "," + (int) u[1] + "]";
        public String d = "u2_B:  [" + (int) u[2] + "," + (int) u[3] + "]";
        public String e = "u1´_R: [" + (int) ustrich[0] + "," + (int) ustrich[1] + "]";
        public String f = "u2´_B: [" + (int) ustrich[2] + "," + (int) ustrich[3] + "]";
        public String h = "v1_R:  [" + (int) v[0] + "," + (int) v[1] + "]";
        public String n = "v2_B:  [" + (int) v[2] + "," + (int) v[3] + "]";
        public String j = "v1´:R: [" + (int) (ustrich[0] + V[0]) + "," + (int) (ustrich[1] + V[1]) + "]";
        public String k = "v2´_B: [" + (int) (ustrich[2] + V[0]) + "," + (int) (ustrich[3] + V[1]) + "]";
        public String l = "V:     [" + ((int) V[0]) + "," + ((int) V[1]) + "]";


        // set this panel's preferred size for auto-sizing the container JFrame
        public Dimension getPreferredSize() {
            return new Dimension(Konstanten.WINDOW_WIDTH+100, Konstanten.WINDOW_HEIGHT);
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

                k1.el=el;
                k2.el=el;
                ustrich=Physik.Ball_Collision(Physik.bvec(k1,k2),k1, k2,el);
                v[0]=(int) k1.vX;
                v[1]=(int) k1.vY;
                v[2]=(int) k2.vX;
                v[3]=(int) k2.vY;
                V=Physik.Schwerpunkt_Geschwindigkeit(k1, k2);
                u=Physik.cms(k1, k2);

                if(Control_Panel.Reset==true){
                    Control_Panel.Reset=false;
                    k1=new Ball(s1);
                    k2=new Ball(s2);
                }
                if(Control_Panel.exp1==true){
                    Control_Panel.exp1=false;
                    k1.vY=0;
                    k1.vX=200;
                    k1.currentX=200;
                    k1.currentY=height/2;
                    k2.currentX=width/2;
                    k2.currentY=500;
                    k2.vX=0;
                    k2.vY=-200;
                }
                if(Control_Panel.exp2==true){
                    Control_Panel.exp2=false;
                    k1.vY=0;
                    k1.vX=200;
                    k1.currentX=200;
                    k1.currentY=height/2-30;
                    k2.currentX=width-200;
                    k2.currentY=height/2;
                    k2.vX=0;
                    k2.vY=0;
                }
                k1.draw(g,new Color(0f,0f,1f,0.5f));
                k2.draw(g,new Color(1f,0f,0f,0.5f));
                double[] swp=Physik.Schwerpunkt_Position(k1, k2);
                double[]swg=Physik.Schwerpunkt_Geschwindigkeit(k1, k2);
                if(true==Control_Panel.Start){
                    k1.moveInArea(deltaTime,width,height);
                    k2.moveInArea(deltaTime,width,height);
                }
                if(k1.diameter/2+k2.diameter/2>=Physik.dis(k1,k2)){
                    Physik.finalcollisionresponds(k1, k2, el);

                    c = "u1_R:  [" + (int) u[0] + "," + (int) u[1] + "]";
                    d = "u2_B:  [" + (int) u[2] + "," + (int) u[3] + "]";
                    e = "u1´_R: [" + (int) ustrich[0] + "," + (int) ustrich[1] + "]";
                    f = "u2´_B: [" + (int) ustrich[2] + "," + (int) ustrich[3] + "]";
                    h = "v1_R:  [" + (int) v[0] + "," + (int) v[1] + "]";
                    n = "v2_B:  [" + (int) v[2] + "," + (int) v[3] + "]";
                    j = "v1´:R: [" + (int) (ustrich[0] + V[0]) + "," + (int) (ustrich[1] + V[1]) + "]";
                    k = "v2´_B: [" + (int) (ustrich[2] + V[0]) + "," + (int) (ustrich[3] + V[1]) + "]";
                }
                a = "bx:     [" + (int) Physik.bvec(k1, k2)[0] + "," + (int) Physik.bvec(k1, k2)[1] + "]";
                b = "|b|:    " + (int) Physik.dis(k1, k2);
                l = "V:     [" + ((int) V[0]) + "," + ((int) V[1]) + "]";
                g2d.setStroke(new BasicStroke(5.0f)); //line width

                g.setColor(Color.BLACK);
                g.drawLine(originX + 150, originY, originX, originY + 150); //band oben links

                g.setColor(Color.BLACK);
                g.drawLine(originX + 150, originY + height, originX, originY + height - 150); //band unten links

                g.setColor(Color.BLACK);
                g.drawLine(originX + width - 150, originY, originX + width, originY + 150); //band oben rechts

                g.setColor(Color.BLACK);
                g.drawLine(originX + width - 150, originY + height, originX + width, originY + height - 150); //band unten rechts
                g2d.setStroke(new BasicStroke(2.0f));
                g.setColor(Color.black);
                g.drawLine((int)k1.currentX+(int)offset,(int)k1.currentY+(int)offset ,(int)k2.currentX+(int)offset,(int)k2.currentY+(int)offset);
                g.setColor(Color.green);
                g.fillOval((int)swp[0]+(int)diameter/4,(int)swp[1]+(int)diameter/4,(int)diameter/2,(int)diameter/2);
                //System.out.println("hallo")
                    g.setColor(Color.black);
                    g.drawString(a,width+10,height/3);
                    g.drawString(b,width+10,height/3+15);
                    g.drawString(c,width+10,height/3+30);
                    g.drawString(d,width+10,height/3+45);
                    g.drawString(e,width+10,height/3+60);
                    g.drawString(f,width+10,height/3+75);
                    g.drawString(h,width+10,height/3+90);
                    g.drawString(n,width+10,height/3+105);
                    g.drawString(j,width+10,height/3+120);
                    g.drawString(k,width+10,height/3+135);
                    g.drawString(l,width+10,height/3+150);
        }

    }
}
