package eg.edu.alexu.csd.datastructure.linkedList;

public class Polynomials implements IPolynomialSolver {
    private int[][]A,B,C;

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        poly=checkPoly(poly);
        switch(poly){
            case 'A':A=terms;
            case 'B':B=terms;
            case 'C':C=terms;
        }
        System.out.println("Polynomial "+poly+" is set.");
    }

    @Override
    public String print(char poly) {
        return null;
    }

    @Override
    public void clearPolynomial(char poly) {
        poly=checkPoly(poly);
        switch(poly){
            case 'A':A=null;
            case 'B':B=null;
            case 'C':C=null;
        }

    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        poly=checkPoly(poly);
        int[][]polynomial=null;
        switch (poly){
            case 'A':polynomial=A;
            case 'B':polynomial=B;
            case 'C':polynomial=C;
        }
        if(polynomial==null||polynomial.length==0){
            System.out.println(poly+" is not set");
            return 0;
        }
        float R=0;
        for(int i=0;i<polynomial.length;i++){
            R=(float)(polynomial[i][0]*Math.pow(value,polynomial[i][1]));
        }
        return R;
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
        throw new RuntimeException();

    }
}
