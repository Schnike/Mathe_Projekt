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
            vX = -vX*el;
            currentX = currentX - 1;

        } else if (currentX <= 0) { // else if to prevent double-checking hence saving performance
            // Object has hit the left-hand wall
            vX = -vX*el;
            currentX = currentX + 1;
        }


        if (currentY >= height - diameter) {
            // Object has hit the floor
            vY = -vY*el;
            currentY = currentY - 1;

        } else if (currentY <= 0) {
            // Object has hit the ceiling
            vY = -vY*el;
            currentY = currentY + 1;
        }

    }
}
