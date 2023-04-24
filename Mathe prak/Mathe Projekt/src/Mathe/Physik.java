package Mathe;

public class Physik {
    public static double[] Banden_Kuss(double[] n, double vx, double vy){
        double v[]={vx, vy};
        double nn[]= {n[0]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1]),n[1]/(int)Math.sqrt(n[0]*n[0]+n[1]*n[1])};
        double vsenk[]=new double[2];
        double vp[] = new double[2];

        vsenk[0]=v[0]-(1*vp[0]);
        vsenk[1]=v[1]-(1*vp[1]);

        double []w= {vsenk[0]-vp[0],vsenk[1]-vp[1]};

        return w;
    }
    public static boolean ball_treffen (double X1,double Y1, double diameter_k1, double X2, double Y2, double diameter_k2){


        //rechne vektor Z1->Z2 (anfang - ende -> Z2-Z1)
        double XB = 0; //x unterschied
        double YB = 0; //y unterschied
        XB = X2 - X1;
        YB = Y2 - Y1;

        //rechne laenge vektor Z1->Z2
        if(Math.sqrt(Math.pow(XB, 2)+Math.pow(YB, 2)) <= diameter_k1/2 + diameter_k2/2){
            System.out.print("sheeeeeeeeeesh");
            return true;

        }
        else return false;

    }
}
