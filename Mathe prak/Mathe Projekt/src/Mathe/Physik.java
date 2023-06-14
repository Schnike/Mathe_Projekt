package Mathe;

public class Physik {



    public static double[] Banden_Collision(double[] n, double vx, double vy, double el){
        double v[]={vx, vy};
        double nn[]= {n[0]/ Math.sqrt(n[0]*n[0]+n[1]*n[1]),n[1]/Math.sqrt(n[0]*n[0]+n[1]*n[1])};
        double vsenk[]= new double[2];
        double vp[] = new double[2];
        double vPar=((v[0]*nn[0])+(v[1]*nn[1]))/((nn[0]*nn[0])+(nn[1]*nn[1]));
        vp[0]=vPar*nn[0];
        vp[1]=vPar*nn[1];
        vsenk[0]=v[0]-vp[0];
        vsenk[1]=v[1]-vp[1];
        double []w= {((-el)*vp[0])+vsenk[0],((-el)*vp[1])+vsenk[1]};
        return w;
    }

    public static double[] Ball_Collision(double[] n,Ball k1, Ball k2, double el){
        double nn[]= {n[0]/Math.sqrt(n[0]*n[0]+n[1]*n[1]),n[1]/Math.sqrt(n[0]*n[0]+n[1]*n[1])};
        double u[]= cms(k1, k2);
        double usenk[]=new double[4];
        double upar[] = new double[4];

        double u1par=((u[0]*nn[0])+(u[1]*nn[1]))/((nn[0]*nn[0])+(nn[1]*nn[1]));  //u1
        upar[0]=u1par*nn[0];
        upar[1]=u1par*nn[1];

        usenk[0]=u[0]-(el*upar[0]);
        usenk[1]=u[1]-(el*upar[1]);

        double u2par=((u[2]*nn[0])+(u[3]*nn[1]))/((nn[0]*nn[0])+(nn[1]*nn[1]));  //u2
        upar[2]=u2par*nn[0];
        upar[3]=u2par*nn[1];

        usenk[2]=u[2]-(1*upar[2]);
        usenk[3]=u[3]-(1*upar[3]);

        double []U= {usenk[0]-upar[0],usenk[1]-upar[1],     //u1´
                usenk[2]-upar[2],usenk[3]-upar[3]};		//u2´
        return U;
    }
    public static double[] Schwerpunkt_Position(Ball k1, Ball k2){
        double X[]={((k1.m*k1.currentX)+ (k2.m*k2.currentX))/(k1.m+k2.m),((k1.m*k1.currentY)+ (k2.m*k2.currentY))/(k1.m+k2.m)};
        return X;
    }
    public static double[] Schwerpunkt_Geschwindigkeit(Ball k1, Ball k2){
        double V[]={(1/(k1.m+k2.m))*(k1.m*k1.vX+k2.m*k2.vX),(1/(k1.m+k2.m))*(k1.m*k1.vY+k2.m*k2.vY)};
        return V;
    }

    public static double []cms(Ball k1,Ball k2){

        double[] u=new double[4];

        u[0]=(k2.m/(k1.m+k2.m))*(k1.vX-k2.vX);			//u1
        u[1]=(k2.m/(k1.m+k2.m))*(k1.vY-k2.vY);

        u[2]=(k1.m/(k1.m+k2.m))*(k2.vX-k1.vX);			//u2
        u[3]=(k1.m/(k1.m+k2.m))*(k2.vY-k1.vY);

        return u;
    }

    public static double[] bvec(Ball k1, Ball k2) {
        double xdiff= ((k2.currentX)-(k1.currentX));
        double ydiff= ((k2.currentY)-(k1.currentY));
        double values[]= {xdiff,ydiff};

        return values;
    }

    public static double[] par(double n[],Ball k1,Ball k2) {
        double nn[]= {n[0],n[1]};
        double v[]= cms(k1, k2);
        double upar[] = new double[4];

        double u1par=((v[0]*nn[0])+(v[1]*nn[1]))/((nn[0]*nn[0])+(nn[1]*nn[1]));  //u1
        upar[0]=u1par*nn[0];
        upar[1]=u1par*nn[1];

        double u2par=((v[2]*nn[0])+(v[3]*nn[1]))/((nn[0]*nn[0])+(nn[1]*nn[1]));  //u2
        upar[2]=u2par*nn[0];
        upar[3]=u2par*nn[1];



        upar[0]=upar[0]/ Math.sqrt(upar[0]*upar[0]+upar[1]*upar[1]);
        upar[1]=upar[1]/ Math.sqrt(upar[0]*upar[0]+upar[1]*upar[1]);
        upar[2]=upar[2]/ Math.sqrt(upar[2]*upar[2]+upar[3]*upar[3]);
        upar[3]=upar[3]/ Math.sqrt(upar[2]*upar[2]+upar[3]*upar[3]);


        return upar;

    }

    public static void finalcollisionresponds(Ball k1,Ball k2, double el){
        double V[]=Physik.Schwerpunkt_Geschwindigkeit(k1, k2);
        double u[]=Physik.Ball_Collision(Physik.bvec(k1,k2),k1, k2, el);
        double par[]=Physik.par(Physik.bvec(k1,k2), k1, k2);


        k1.vX=u[0]+V[0];
        k1.vY=u[1]+V[1];
        k1.currentX=k1.currentX+par[2]*3;				//Offset 3
        k1.currentY=k1.currentY+par[3]*3;

        k2.vX=u[2]+V[0];
        k2.vY=u[3]+V[1];
        k2.currentX=k2.currentX+par[0]*3;			//Offset 3
        k2.currentY=k2.currentY+par[1]*3;

    }

    public static double dis(Ball k1,Ball k2)  {
        double xdiff= ((k1.currentX)-(k2.currentX));
        double ydiff= ((k1.currentY)-(k2.currentY));
        double values[]= {xdiff,ydiff};
        double distance= Math.sqrt((values[0]*values[0])+(values[1]*values[1]));
        return distance;
    }
}
