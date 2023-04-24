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

    public Ball(double currentX, double currentY, double diameter, double vX, double vY) {
        this.currentX=currentX;
        this.currentY=currentY;
        this.diameter=diameter;
        this.vX=vX;
        this.vY=vY;
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
            // Object has hit the right-hand wall
            vX = -vX;
            currentX = currentX - 1;

        }
        if (currentX <= 0) { // else if to prevent double-checking hence saving performance
            // Object has hit the left-hand wall
            vX = -vX;
            currentX = currentX + 1;
        }
        if (currentY >= height - diameter) {
            // Object has hit the floor
            vY = -vY;
            currentY = currentY - 1;

        }
        if (currentY <= 0) {
            // Object has hit the ceiling
            vY = -vY;
            currentY = currentY + 1;
        }
        if (-currentX-currentY+100+(diameter/2)>0){ // obere like ecke
            double v[]=Physik.Ball_Kuss(normal1, vX, vY);
            vX=v[0];
            vY=v[1];
            currentY = currentY + 1;
            currentX = currentY +1;
        }


    }
}
