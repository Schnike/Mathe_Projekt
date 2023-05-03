package Mathe;

public class Physik {
    public static double[] Banden_Collision(double[] n, double vx, double vy, double el){
        double v[]={vx, vy};
        double nn[]= {n[0]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1]),n[1]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1])};
        double vsenk[]=new double[2];
        double vp[] = new double[2];
        double vPar=((v[0]*nn[0])+(v[1]*nn[1]))/((nn[0]*n[0])+(nn[1]*nn[1]));
        vp[0]=vPar*nn[0];
        vp[1]=vPar*nn[1];
        vsenk[0]=v[0]-(el*vp[0]);
        vsenk[1]=v[1]-(el*vp[1]);

        double []w= {vsenk[0]-vp[0],vsenk[1]-vp[1]};

        return w;
    }

    public static void Ball_Collision(Ball k1, Ball k2, double eR, double eB){
        double ball1[]={k1.currentX, k1.currentY};
        double v1[]={k1.vX,k1.vY};
        double ball2[]={k2.currentX, k2.currentY};
        double v2[]={k2.vX,k2.vY};
        double b_ein[]={ball1[0]-ball2[0],ball1[1]-ball2[1]};// ball2 -> ball1
        double bl=Math.sqrt(Math.pow(b_ein[0],2)+Math.pow(b_ein[1],2));
        double b[]={(1/bl)*b_ein[0],(1/bl)*b_ein[1]};
        if(bl <= k1.diameter/2 + k2.diameter/2){
            k2.vX=v1[0]-eB*b[0];
            k2.vY=v1[1]-eB*b[1];
            k1.vX=v2[0]+eR*b[0];
            k1.vY=v2[1]+eR*b[1];
        }
    }
    public static double[] Schwerpunkt_Position(Ball k1, Ball k2){
        double X[]={((k1.m*k1.currentX)+ (k2.m*k2.currentX))/(k1.m+k2.m),((k1.m*k1.currentY)+ (k2.m*k2.currentY))/(k1.m+k2.m)};
        return X;
    }
    public static double[] Schwerpunkt_Geschwindigkeit(Ball k1, Ball k2){
        double V[]={(1/(k1.m+k2.m))*(k1.m*k1.vX+k2.m*k2.vX),(1/(k1.m+k2.m))*(k1.m*k1.vY+k2.m*k2.vY)};
        return V;
    }
    public static void cm(){

    }
}
