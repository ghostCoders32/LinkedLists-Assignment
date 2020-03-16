package eg.edu.alexu.csd.datastructure.linkedList;


public class Polynomials implements IPolynomialSolver {

    SingleLinked A = new SingleLinked();
    SingleLinked B = new SingleLinked();
    SingleLinked C = new SingleLinked();


    @Override
    public void setPolynomial(char poly, int[][] terms) {
        SingleLinked temp = new SingleLinked();
        poly=checkPoly(poly);
        for (int[] term : terms) temp.add(term);
        temp= (SingleLinked) sortList(temp);
        switch (poly){
            case 'A':A=temp;break;
            case 'B':B=temp;break;
            case 'C':C=temp;break;
        }
    }


    @Override
    public String print(char poly) {
        SingleLinked temp = new SingleLinked();
        poly = checkPoly(poly);
        int[] temp1;
        int i=0;
        //The list  is sorted at set.

        switch (poly){
            case 'A':temp=A;break;
            case 'B':temp=B;break;
            case 'C':temp=C;break;
        }

        StringBuilder s = new StringBuilder();
        if(temp.isEmpty())
            return null;
        while (i<temp.size()){
            temp1 = (int[]) temp.get(i);
            if (i != 0) {
                if (temp1[0] < 0) {
                } else
                    s.append("+");
            }
            if (temp1[0] == 1 || temp1[0] == 0){}
            else s.append(temp1[0]);
            if (temp1[1] == 0){}
            else s.append("x^").append(temp1[1]);
            i++;
        }

        return s.toString();
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
        SingleLinked temp = new SingleLinked();
        poly=checkPoly(poly);
        switch (poly){
            case 'A':temp=A;break;
            case 'B':temp=B;break;
            case 'C':temp=C;break;
        }
        float ans =0;
        int i=0;
        int[] temp1 ;
        while (temp.hasNext(i)){
            temp1 = (int[]) temp.get(i);
            ans+=(float) temp1[0]* Math.pow(value,temp1[1]);
            i++;
        }
        return ans;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        SingleLinked polynomial1=null;
        SingleLinked polynomial2=null;
        switch (poly1){
            case 'A':polynomial1=A;break;
            case 'B':polynomial1=B;break;
            case 'C':polynomial1=C;break;
        }
        switch (poly2){
            case 'A':polynomial2=A;break;
            case 'B':polynomial2=B;break;
            case 'C':polynomial2=C;break;
        }
        if(polynomial1==null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if(polynomial2==null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if(polynomial1.size()==0&&polynomial2.size()==0) {
            System.out.println("We are here");
            return new int[][]{};
        }

        int[][] R=new int[polynomial1.size()+polynomial2.size()][2];
        int counter=0,i=0,j=0;
        while(i<polynomial1.size()||j<polynomial2.size()){
            int[] temp1;
            int[] temp2;
            if(j==polynomial2.size()) {
                temp1=(int[])polynomial1.get(i);
                R[counter] = temp1;
                i++;
            }
            else if(i==polynomial1.size()) {
                temp2=(int[])polynomial2.get(j);
                R[counter] = temp2;
                j++;
            }
            else {
                temp1 = (int[]) polynomial1.get(i);
                temp2 = (int[]) polynomial2.get(j);

                if (temp1[1] > temp2[1]) {
//               R[counter][0]=polynomial1[i][0];
//               R[counter][1]=polynomial1[i][1];
                    R[counter] = temp1;
                    i++;
                } else if (temp1[1] < temp2[1]) {
                    R[counter] = temp2;
                    j++;
                } else {//Equal powers
                    R[counter][0] = temp1[0] + temp2[0];
                    R[counter][1] = temp2[1];
                    i++;
                    j++;
                }
            }
            counter++;
        }
        return R;
    }
    @Override
    public int[][] subtract(char poly1, char poly2) {
        poly1=checkPoly(poly1);
        poly2=checkPoly(poly2);
        SingleLinked polynomial1=null;
        SingleLinked polynomial2=null;
        switch (poly1){
            case 'A':polynomial1=A;break;
            case 'B':polynomial1=B;break;
            case 'C':polynomial1=C;break;
        }
        switch (poly2){
            case 'A':polynomial2=A;break;
            case 'B':polynomial2=B;break;
            case 'C':polynomial2=C;break;
        }
        if(polynomial1==null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if(polynomial2==null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if(polynomial1.size()==0&&polynomial2.size()==0)
            return new int[][]{};

        int[][] R=new int[polynomial1.size()+polynomial2.size()][2];
        int counter=0,i=0,j=0;
        while(i<polynomial1.size()||j<polynomial2.size()){
            int[] temp1;
            int[] temp2;
            if(j==polynomial2.size()) {
                temp1=(int[])polynomial1.get(i);
                R[counter] = temp1;
                i++;
            }
            else if(i==polynomial1.size()) {
                temp2=(int[])polynomial2.get(j);
                R[counter][0] = -temp2[0];
                R[counter][1] = temp2[1];
                j++;
            }
            else {
                temp1 = (int[]) polynomial1.get(i);
                temp2 = (int[]) polynomial2.get(j);

                if (temp1[1] > temp2[1]) {
//               R[counter][0]=polynomial1[i][0];
//               R[counter][1]=polynomial1[i][1];
                    R[counter] = temp1;
                    i++;
                } else if (temp1[1] < temp2[1]) {
                    R[counter][0] = -temp2[0];
                    R[counter][1] = temp2[1];
                    j++;
                } else {//Equal powers
                    R[counter][0] = temp1[0] - temp2[0];
                    R[counter][1] = temp2[1];
                    i++;
                    j++;
                }
            }
            counter++;
        }
        return R;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        poly1 = checkPoly(poly1);
        poly2 = checkPoly(poly2);
        SingleLinked polynomial1 = null;
        SingleLinked polynomial2 = null;
        switch (poly1) {
            case 'A':
                polynomial1 = A;break;
            case 'B':
                polynomial1 = B;break;
            case 'C':
                polynomial1 = C;break;
        }
        switch (poly2) {
            case 'A':
                polynomial2 = A;break;
            case 'B':
                polynomial2 = B;break;
            case 'C':
                polynomial2 = C;break;
        }
        if (polynomial1 == null) {
            System.out.println(poly1 + " is not set.");
            return null;
        }
        if (polynomial2 == null) {
            System.out.println(poly2 + " is not set.");
            return null;
        }
        if (polynomial1.size() == 0)
            return new int[][]{};
        else if (polynomial2.size() == 0)
            return new int[][]{};
        int[][] R = new int[polynomial1.size() * polynomial2.size()][2];
        int counter = 0;
        for (int i = 0; i < polynomial1.size(); i++) {
            for (int j = 0; j < polynomial2.size(); j++) {
                int[] temp1 = (int[]) polynomial1.get(i);
                int[] temp2 = (int[]) polynomial2.get(j);
                R[counter][0] = temp1[0] * temp2[0];
                R[counter][1] = temp1[1] + temp2[1];
                counter++;
            }
        }
        //The result must be sorted.
        SingleLinked x=A;
        setPolynomial('A',R);
        R=new int[A.size()][2];
        //A is sorted .
        for(int i=0;i<A.size();i++){
            R[i]=(int[])A.get(i);
        }
        A=x;
        R=checkPowers(R);
        return R;
    }

    public char checkPoly(char poly){
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
    public ILinkedList sortList(ILinkedList x){
        System.out.println("size is "+x.size());
        for(int i=0;i<x.size();i++) {
            for (int j = 0; j < (x.size()- 1); j++) {
                int[] temp1 = (int[]) x.get(j);
                int[] temp2 = (int[]) x.get(j + 1);
                if (temp1[1] < temp2[1]) {
                    x.set(j, temp2);
                    x.set(j + 1, temp1);
                }

            }
        }
        return x;
    }

    public int [][] checkPowers (int [][] x){
        int l=0,k=0,p=0;
        for (int i=0;i<x.length;i++) {
            for (int j = i + 1; j < x.length; j++) {
                if (x[i][1] == x[j][1]) {
                    x[j][1] = -98989;
                    x[i][0] = x[i][0] + x[j][0];
                    k++;
                }
            }
        }
            int[][] y=new int[x.length-k][2];
            while (l<y.length){
                if (x[p][1]!=-98989){
                    y[l]=x[p];
                    l++;
                }
                p++;
            }
       return y;
    }
}
