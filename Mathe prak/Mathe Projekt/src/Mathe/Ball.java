package Mathe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Ball {
    double currentX;
    double currentY;
    double diameter;
    double vX;
    double vY;
    double m;

    double normal1[]={1,1};
    double normal2[]={-1,1};
    double v[];

    public Ball(double currentX, double currentY, double diameter, double vX, double vY, double m) {
        this.currentX=currentX;
        this.currentY=currentY;
        this.diameter=diameter;
        this.vX=vX;
        this.vY=vY;
        this.m=m;
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
        //g2.setColor(Color.black);
        //g2.drawLine((int)currentX,(int)currentY ,(int)currentX+(int)(vX),(int)currentY+(int)(vY));
    }
    public void moveInArea(double deltaTime, double width, double height,double el){
        currentX = currentX + (vX * deltaTime);
        currentY = currentY + (vY * deltaTime);

        if (currentX >= width - diameter) {
            // rechte Wand
            vX = -vX*el;
            currentX = currentX - 1;

        }
        if (currentX <= 0) {
            // Linke Wand
            vX = -vX*el;
            currentX = currentX + 1;
        }
        if (currentY >= height - diameter) {
            // Untere Wand
            vY = -vY*el;
            currentY = currentY - 1;

        }
        if (currentY <= 0) {
            // Obere Wand
            vY = -vY*el;
            currentY = currentY + 1;
        }
        if (-currentX-currentY+100+(diameter/2)>0){ // obere like ecke
            v=Physik.Banden_Collision(normal1, vX, vY,el);
            vX=v[0];
            vY=v[1];
            currentY = currentY + 3;
            currentX = currentX + 3;
        }
        if ((-currentX+width)-currentY+400-(diameter/2)<0){ // untere rechte ecke
            v=Physik.Banden_Collision(normal1, vX, vY,el);
            vX=v[0];
            vY=v[1];
            currentY = currentY - 3;
            currentX = currentX - 3;
        }
        if ((width-currentX)-(150-currentY)-(diameter/2)<0){ // obere rechte ecke
            v=Physik.Banden_Collision(normal2,vX,vY,el);
            vY=v[1];
            vX=v[0];
            currentX = currentX - 3;
            currentY = currentY + 3;
        }
        if (currentX-currentY+450-(diameter/2)<0){ //untere linke ecke
            v=Physik.Banden_Collision(normal2,vX,vY,el);
            vX=v[0];
            vY=v[1];
            currentX = currentX + 3;
            currentY = currentY - 3;
        }
    }
}
