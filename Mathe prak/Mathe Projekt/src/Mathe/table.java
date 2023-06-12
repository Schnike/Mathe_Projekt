package Mathe;

public class table {

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
}
