package eg.edu.alexu.csd.datastructure.linkedList;

import java.awt.event.MouseAdapter;

public class Polynomials implements IPolynomialSolver {

    SingleLinked A = new SingleLinked();
    SingleLinked B = new SingleLinked();
    SingleLinked C = new SingleLinked();
    SingleLinked R = new SingleLinked();
    SingleLinked temp = new SingleLinked();


    @Override
    public void setPolynomial(char poly, int[][] terms) {
        poly=checkPoly(poly);
        int n = terms.length;
        for(int i =0; i<n;i++)
            temp.add(i,terms[i]);
        switch (poly){
            case 'A':A=temp;
            case 'B':B=temp;
            case 'C':C=temp;
        }
    }


    @Override
    public String print(char poly) {
        poly = checkPoly(poly);
        int[] temp1;
        int i=0;
        switch (poly){
            case 'A':temp=A;
            case 'B':temp=B;
            case 'C':temp=C;
        }
       /////////////////////////////// /////sort list temp
        String s ="";
            if(temp.isEmpty())
                return null;
            while (temp.hasNext(i)){
                temp1 = (int[]) temp.get(i);
                if (i==0) {
                    if (temp1[0] == 1 || temp1[0] == 0){}
                    else if (temp1[0] != 0 && temp1[0] != 1)
                        s += temp1[0];
                    if (temp1[1] == 0){}
                    else if (temp1[1] != 0)
                        s += "x^" + temp1[1];
                }
                else {
                    if (temp1[0]<0){}
                    else
                        s+="+";
                    if (temp1[0] == 1 || temp1[0] == 0){}
                    else if (temp1[0] != 0 && temp1[0] != 1)
                        s += temp1[0];
                    if (temp1[1] == 0){}
                    else if (temp1[1] != 0)
                        s += "x^" + temp1[1];
                }
                i++;
            }

        return s;
    }

    @Override
    public void clearPolynomial(char poly) {
        poly=checkPoly(poly);
      if(poly == 'A')
       A.clear();
      else if (poly=='B')
          B.clear();
      else
          C.clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        poly=checkPoly(poly);
        switch (poly){
            case 'A':temp=A;
            case 'B':temp=B;
            case 'C':temp=C;
        }
        float ans =0;
        int i=0;
        int[] temp1 ;
        while (temp.hasNext(i)){
            temp1 = (int[]) temp.get(i);
            ans+=temp1[0]* Math.pow(value,temp1[1]);

        }
        return ans;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        poly1=checkPoly(poly1);
        poly2=checkPoly(poly2);
        int[][]polynomial1=null;
        int[][]polynomial2=null;
        switch (poly1){
            case 'A':polynomial1=A;
            case 'B':polynomial1=B;
            case 'C':polynomial1=C;
        }
        switch (poly2){
            case 'A':polynomial2=A;
            case 'B':polynomial2=B;
            case 'C':polynomial2=C;
        }
        if(polynomial1==null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if(polynomial2==null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if(polynomial1.length==0&&polynomial2.length==0)
            return new int[][]{};
        else if(polynomial1.length==0)
            return polynomial2;
        else if(polynomial2.length==0)
            return polynomial1;
        int[][] R=new int[polynomial1.length+polynomial2.length][2];
        int counter=0,i=0,j=0;
        while(i<polynomial1.length||j<polynomial2.length){
            if(polynomial1[i][1]>polynomial2[j][1]){
//               R[counter][0]=polynomial1[i][0];
//               R[counter][1]=polynomial1[i][1];
                R[counter]=polynomial1[i];
                i++;
            }
            else if(polynomial1[i][1]<polynomial2[j][1]){
                R[counter]=polynomial2[j];
                j++;
            }
            else{//Equal powers
                R[counter][0]=polynomial1[i][0]+polynomial2[j][0];
                R[counter][1]=polynomial2[j][1];
                i++;
                j++;
            }
            if(j==polynomial2.length) {
                R[counter] = polynomial1[i];
                i++;
            }
            if(i==polynomial1.length) {
                R[counter] = polynomial1[j];
                j++;
            }
            counter++;
        }
        return R;
    }
    @Override
    public int[][] subtract(char poly1, char poly2) {
        poly1=checkPoly(poly1);
        poly2=checkPoly(poly2);
        int[][]polynomial1=null;
        int[][]polynomial2=null;
        switch (poly1){
            case 'A':polynomial1=A;
            case 'B':polynomial1=B;
            case 'C':polynomial1=C;
        }
        switch (poly2){
            case 'A':polynomial2=A;
            case 'B':polynomial2=B;
            case 'C':polynomial2=C;
        }
        if(polynomial1==null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if(polynomial2==null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if(polynomial1.length==0&&polynomial2.length==0)
            return new int[][]{};
        else if(polynomial1.length==0)
            return polynomial2;
        else if(polynomial2.length==0)
            return polynomial1;
        int[][] R=new int[polynomial1.length+polynomial2.length][2];
        int counter=0,i=0,j=0;
        while(i<polynomial1.length||j<polynomial2.length){
            if(polynomial1[i][1]>polynomial2[j][1]){
//               R[counter][0]=polynomial1[i][0];
//               R[counter][1]=polynomial1[i][1];
                R[counter]=polynomial1[i];
                i++;
            }
            else if(polynomial1[i][1]<polynomial2[j][1]){
                R[counter][0]=-polynomial2[j][0];
                R[counter][1]=polynomial2[j][0];
                j++;
            }
            else{//Equal powers
                R[counter][0]=polynomial1[i][0]-polynomial2[j][0];
                R[counter][1]=polynomial2[j][1];
                i++;
                j++;
            }
            if(j==polynomial2.length) {
                R[counter] = polynomial1[i];
                i++;
            }
            if(i==polynomial1.length) {
                R[counter][0]=-polynomial2[j][0];
                R[counter][1]=polynomial2[j][0];
                j++;
            }
            counter++;
        }
        return R;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        poly1 = checkPoly(poly1);
        poly2 = checkPoly(poly2);
        int[][] polynomial1 = null;
        int[][] polynomial2 = null;
        switch (poly1) {
            case 'A':
                polynomial1 = A;
            case 'B':
                polynomial1 = B;
            case 'C':
                polynomial1 = C;
        }
        switch (poly2) {
            case 'A':
                polynomial2 = A;
            case 'B':
                polynomial2 = B;
            case 'C':
                polynomial2 = C;
        }
        if (polynomial1 == null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if (polynomial2 == null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if (polynomial1.length == 0)
            return new int[][]{};
        else if (polynomial2.length == 0)
            return new int[][]{};
        int[][]R=new int[polynomial1.length*polynomial2.length][2];
        int counter=0;
        for(int i=0;i<polynomial1.length;i++)
            for (int j=0;j<polynomial2.length;j++){
                R[counter][0]=polynomial1[i][0]*polynomial2[j][0];
                R[counter][1]=polynomial1[i][1]+polynomial2[j][1];
                counter++;
            }
        //Now the result needs to be sorted in the other class.
        return R;
    }

    private char checkPoly(char poly){
        if(poly=='a'||poly=='A')
            return 'A';
        if(poly=='b'||poly=='B')
            return 'B';
        if(poly=='c'||poly=='C')
            return 'C';
        if(poly=='r'||poly=='R')
            return 'R';
        throw new RuntimeException();

    }
}
