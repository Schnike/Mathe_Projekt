package Mathe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball {
    double currentX;
    double currentY;
    double diameter;
    double vX;
    double vY;
    double normal1[]={1,1};
    double normal2[]={-1,1};
    double v[];

    public Ball(double currentX, double currentY, double diameter, double vX, double vY) {
        this.currentX=currentX;
        this.currentY=currentY;
        this.diameter=diameter;
        this.vX=vX;
        this.vY=vY;
    }
    public Ball(Ball k1)
    {
        this.currentX = k1.currentX;
        this.currentY = k1.currentY;
        this.diameter = k1.diameter;
        this.vX = k1.vX;
        this.vY = k1.vY;
    }
    public void draw(Graphics g,Color c) {
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(c);
        g2.fillOval( (int)(currentX), (int)(currentY), (int)diameter, (int)diameter);
    }
    public void moveInArea(double deltaTime, double width, double height,double el){
        currentX = currentX + (vX * deltaTime);
        currentY = currentY + (vY * deltaTime);

        if (currentX >= width - diameter) {
            // rechte Wand
            vX = -vX;
            currentX = currentX - 1;

        }
        if (currentX <= 0) {
            // Linke Wand
            vX = -vX;
            currentX = currentX + 1;
        }
        if (currentY >= height - diameter) {
            // Untere Wand
            vY = -vY;
            currentY = currentY - 1;

        }
        if (currentY <= 0) {
            // Obere Wand
            vY = -vY;
            currentY = currentY + 1;
        }
        if (-currentX-currentY+100+(diameter/2)>0){ // obere like ecke
            v=Physik.Banden_Kuss(normal1, vX, vY);
            vX=v[0];
            vY=v[1];
            currentY = currentY + 1;
            currentX = currentY + 1;
        }
        if ((-currentX+width)-currentY+height-100-(diameter/2)<0){ // untere rechte ecke
            v=Physik.Banden_Kuss(normal1, vX, vY);
            vX=v[0];
            vY=v[1];
            currentY = currentY - 1;
            currentX = currentY - 1;

        }


    }
}
