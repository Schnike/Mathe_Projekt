package Mathe;

public class Physik {
    public static double[] Ball_Kuss(double[] n, double vx, double vy){
        double v[]={vx, vy};
        double nn[]= {n[0]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1]),n[1]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1])};
        double vsenk[]=new double[2];
        double vp[] = new double[2];

        vsenk[0]=v[0]-(1*vp[0]);
        vsenk[1]=v[1]-(1*vp[1]);

        double []w= {vsenk[0]-vp[0],vsenk[1]-vp[1]};

        return w;
    }
}
