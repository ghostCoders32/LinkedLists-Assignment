package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialsTest {


    @Test
    public void print() {
        Polynomials x= new Polynomials();
        String s;
        int[][] arr = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr);
        s=x.print('A');
        assertEquals("3x^4+2x^3+x^2",s);

    }

    @Test
    public void clearPolynomial() {
        Polynomials x= new Polynomials();
        SingleLinked A = new SingleLinked();
        int[][] arr = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr);
        x.clearPolynomial('A');
        assertEquals(true,A.isEmpty());
    }

    @Test
    public void evaluatePolynomial() {
        Polynomials x= new Polynomials();
        int[][] arr = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr);
        float y = (float) (3*Math.pow(5,4)+2*Math.pow(5,3)+Math.pow(5,2));
        assertEquals(y,x.evaluatePolynomial('A',5),0);

    }

    @Test
    public void add() {
        Polynomials x= new Polynomials();
        int[][] arr1 = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr1);
        int[][] arr2 = {{1,2},{2,3}};
        x.setPolynomial('B',arr2);
        int[][] R = {{3,4},{4,3},{2,2}};
        assertArrayEquals(R,x.add('A','B'));
    }

    @Test
    public void subtract() {
        Polynomials x= new Polynomials();
        int[][] arr1 = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr1);
        int[][] arr2 = {{1,2},{2,3}};
        x.setPolynomial('B',arr2);
        int[][] R = {{3,4},{0,3},{0,2}};
        assertArrayEquals(R,x.subtract('A','B'));
    }

    @Test
    public void multiply() {
        Polynomials x= new Polynomials();
        int[][] arr1 = {{1,2},{2,3},{3,4}};
        x.setPolynomial('A',arr1);
        int[][] arr2 = {{1,2},{2,3}};
        x.setPolynomial('B',arr2);
        int[][] R = {{6,7},{7,6},{4,5},{1,4}};
        assertArrayEquals(R,x.multiply('A','B'));
    }
}