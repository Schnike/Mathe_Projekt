package Mathe;

import utils.ApplicationTime;

import javax.swing.*;
import java.awt.*;

public class table extends JPanel {
    private final ApplicationTime t;
    private double time;
    public static JTable tabelle = new JTable(table.data(Bouncy_Ball.Bouncy_Ball_Panel.k1, Bouncy_Ball.Bouncy_Ball_Panel.k2),table.title());
    private static JPanel panel = new JPanel();
    public table(ApplicationTime thread){
        this.t = thread;
    }
    static void Wertetabelle(ApplicationTime thread){
        JFrame Wertetabelle = new JFrame("Wertetabelle");
        Wertetabelle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Wertetabelle.setLayout(new GridLayout(1, 1, 10, 0)); // manages the layout of panels in the frame
        panel.setLayout(new GridLayout(1, 2, 10, 0));
        Wertetabelle.add(panel);
        Wertetabelle.setVisible(true);
        panel.add(tabelle);
        Wertetabelle.pack();
    }
    public static String[][] data(Ball k1, Ball k2){
        double ustrich[]=Physik.Ball_Collision(Physik.bvec(k1,k2),k1, k2);
        double V[]=Physik.cmv(k1, k2);
        double u[]=Physik.cms(k1, k2);
        int v[]= new int[4];
        v[0]=(int) k1.vX;
        v[1]=(int) k1.vY;
        v[2]=(int) k2.vX;
        v[3]=(int) k2.vY;
        String[][] data = new String[][]{
                {"bx:  "+(int)Physik.bvec(k1,k2)[0], "by:  "+(int)Physik.bvec(k1,k2)[1]},
                {"|b|:  "+(int)Physik.dis(k1, k2),""},
                {"u1:  "+(int)u[0], ""+(int)u[1]},
                {"u2:  "+(int)u[2], ""+(int)u[3]},
                {"u1´:  "+(int)ustrich[0], ""+(int)ustrich[1]},
                {"u2´:  "+(int)ustrich[2], ""+(int)ustrich[3]},
                {"v1:  "+(int)v[0], ""+(int)v[1]},
                {"v2:  "+(int)v[2], ""+(int)v[3]},
                {"v1´:  "+(int)(ustrich[0]+V[0]), ""+(int)(ustrich[1]+V[1])},
                {"v2´:  "+(int)(ustrich[2]+V[0]), ""+(int)(ustrich[3]+V[1])},
                {"V:  "+((int)V[0]), ""+((int)V[1])},
        };
        return data;
    }
    public static String [] title(){
        String[] title = new String[]{
                "A", "B"
        };
        return title;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        table.data(Bouncy_Ball.Bouncy_Ball_Panel.k1, Bouncy_Ball.Bouncy_Ball_Panel.k2);
        tabelle = new JTable(table.data(Bouncy_Ball.Bouncy_Ball_Panel.k1, Bouncy_Ball.Bouncy_Ball_Panel.k2),table.title());
        panel.add(tabelle);
    }
}
