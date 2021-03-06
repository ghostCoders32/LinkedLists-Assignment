package eg.edu.alexu.csd.datastructure.linkedList;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class application {
    private static Scanner in=new Scanner(System.in);
    private static  Polynomials polynomial=new Polynomials();
    static int[][] R ;
    static boolean we = false;

    public static void main(String[] args) {
        System.out.println("\t\t\t\t WELCOME");
        menu();

        int choice;
        boolean exit=false;
        while(!exit){
            choice=getInt();
            switch(choice){
                case 0:exit=true;break;
                case 1:setPolynomial();break;
                case 2:printPolynomial();break;
                case 3:addPolynomials();break;
                case 4:subtractPolynomials();break;
                case 5:multiplyPolynomial();break;
                case 6:evaluatePolynomial();break;
                case 7:clear();break;
                case 8:menu();break;
                default:
                    System.out.println("Error enter valid choice!");
            }
        }
        System.out.println("Press any key to continue.");
        in.nextLine();



    }
    public static void setPolynomial() {
        char poly;
        System.out.println("Insert the variable name: A, B or C");
        poly = getChar();
        poly = polynomial.checkPoly(poly);
        System.out.println("Please enter the number of terms of the polynomial.");
        int terms = getInt();
        if (terms <= 0 || terms > 1000)
            throw new ArithmeticException("Invalid number of terms");
        int[][] input = new int[terms][2];
        for (int i = 0; i < terms; i++) {
            System.out.print("Enter the coefficient of " + (i + 1) + "th term:");
            input[i][0] = getInt();
            System.out.print("Enter the exponent " + (i + 1) + "th term:");
            input[i][1] = getInt();
        }
        polynomial.setPolynomial(poly,input);
        System.out.println("Polynomial" + poly + " is successfully set.");
        System.out.println(polynomial.print(poly));
    }
    public static void printPolynomial(){
        char poly;
        if (we == true){
            System.out.println("Insert the variable name: A, B , C or R");
            poly = getChar();
            if (poly =='R'||poly=='r'){
                for (int i = 0; i < R.length; i++) {
                    if (R[i][0] == 0 && R[i][1] == 0)
                        break;
                    System.out.print(R[i][0]+"X"+"^"+R[i][1]);
                    if (R[i][0]>0)
                        if (i==R.length-2)
                            break;
                        System.out.print("+");
                }
                System.out.println();
            }
            else {
                poly = polynomial.checkPoly(poly);
                if ((poly == 'A' && polynomial.getA() == null) || (poly == 'C' && polynomial.getC() == null) || (poly == 'B' && polynomial.getB() == null)) {
                    System.out.println(poly + " is not set.");
                    return;
                }
                System.out.println(polynomial.print(poly));
            }
        }
        else {
            System.out.println("Insert the variable name: A, B or C ");
            poly = getChar();
            if (poly =='R'||poly=='r'){
                System.out.println("R is not set");
                return;
            }
            poly = polynomial.checkPoly(poly);
            if ((poly == 'A' && polynomial.getA() == null) || (poly == 'C' && polynomial.getC() == null) || (poly == 'B' && polynomial.getB() == null)) {
                System.out.println(poly + " is not set.");
                return;
            }
            System.out.println(polynomial.print(poly));
        }
    }
    public static void addPolynomials(){
        char poly1,poly2;
        System.out.println("Insert the variable name of the first polynomial: A, B or C");
        poly1=getChar();
        poly1=polynomial.checkPoly(poly1);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        System.out.println("Insert the variable name of the second polynomial: A, B or C");
        poly2=getChar();
        poly2=polynomial.checkPoly(poly2);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        R=polynomial.add(poly1,poly2);
        we= true;
        printPolynomial(R);
    }
    public static  void subtractPolynomials(){
        char poly1,poly2;
        System.out.println("Insert the variable name of the first polynomial: A, B or C");
        poly1=getChar();
        poly1=polynomial.checkPoly(poly1);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        System.out.println("Insert the variable name of the second polynomial: A, B or C");
        poly2=getChar();
        poly2=polynomial.checkPoly(poly2);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        R=polynomial.subtract(poly1,poly2);
        we =true;
        printPolynomial(R);
    }
    public static  void multiplyPolynomial(){
        char poly1,poly2;
        System.out.println("Insert the variable name of the first polynomial: A, B or C");
        poly1=getChar();
        poly1=polynomial.checkPoly(poly1);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        System.out.println("Insert the variable name of the second polynomial: A, B or C");
        poly2=getChar();
        poly2=polynomial.checkPoly(poly2);
        if((poly1=='A'&&polynomial.getA()==null)||(poly1=='C'&&polynomial.getC()==null)||(poly1=='B'&&polynomial.getB()==null)){
            System.out.println(poly1 + " is not set.");
            return;
        }
        R=polynomial.multiply(poly1,poly2);
        we = true;
        printPolynomial(R);
    }
    public static void evaluatePolynomial(){
        char poly;
        System.out.println("Insert the variable name of the polynomial: A, B or C");
        poly=getChar();
        poly=polynomial.checkPoly(poly);
        if((poly=='A'&&polynomial.getA()==null)||(poly=='C'&&polynomial.getC()==null)||(poly=='B'&&polynomial.getB()==null)){
            System.out.println(poly + " is not set.");
            return;
        }
        float value;
        System.out.println("What is the value to be evaluated?");
        value=getFloat();
        float ans=polynomial.evaluatePolynomial(poly,value);
        System.out.println("The answer is :"+ans);
    }
    public static void clear(){
        System.out.println("Insert the variable name of the polynomial: A, B or C");
        char poly=getChar();
        poly=polynomial.checkPoly(poly);
        polynomial.clearPolynomial(poly);
    }

    public static void printPolynomial(int[][] R){
        if(R!=null) {
            System.out.print("R=");
            if (R.length==0) {
                System.out.print("0\n");
                return;
            }
            for (int i = 0; i < R.length; i++) {
                if (R[i][0] == 0 && R[i][1] == 0)
                    break;
                System.out.print("( " + R[i][0] + " , " + R[i][1] + " )");
            }
            System.out.println();
        }

    }
    public static char getChar(){
        char choice;
        while(true){
            System.out.print("Enter a letter: \r");
            choice=in.next().charAt(0);
            if((choice>='a'&&choice<='z')||(choice>='A'&&choice<='Z'))
                return choice;
            else
                System.out.println("Error\nEnter a valid Letter!");
        }
    }

    public static void menu(){
        System.out.println("Please choose an action.");
        System.out.println("\n-----------------------\n");
        System.out.println("1- Set a polynomial variable\n"+
                "2- Print the value of a polynomial variable\n"+
                "3- Add two polynomials\n"+
                "4- Subtract two polynomials\n"+
                "5- Multiply two polynomials\n"+
                "6- Evaluate a polynomial at some point\n"+
                "7- Clear a polynomial variable\n"+
                "8-To show menu\n"+
                "0-To Exit"
        );

    }
    public static int getInt(){
        while(true){
            System.out.print("Enter your choice: \r");
            int choice;
            try{
                choice=in.nextInt();
                in.nextLine();
                return choice;
            }
            catch(InputMismatchException e){
                in.nextLine();
                System.out.println("Enter valid integer!");
            }
        }

    }
    public static float getFloat(){
        while(true){
            System.out.print("Enter your choice: \r");
            float choice;
            try{
                choice=in.nextFloat();
                in.nextLine();
                return choice;
            }
            catch(InputMismatchException e){
                in.nextLine();
                System.out.println("Enter valid integer!");
            }
        }

    }





}
