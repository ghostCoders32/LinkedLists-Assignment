package eg.edu.alexu.csd.datastructure.linkedList;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLinkedTest {

    @Test
    public void add() {
        SingleLinked A= new SingleLinked();
        A.add("My");       //index 0
        A.add("New");      //index 1
        A.add("Linked");   //index 2
        A.add("List");     //index 3
        A.add("which");    //index 4
        A.add("is");       //index 5
        A.add("Single");   //index 6
        assertEquals("My",A.get(0));
        assertEquals("New",A.get(1));
        A.add(5,"It is added");
        assertEquals("It is added",A.get(5));

        try {
            A.add(10000,"NullPointerException");
            assertEquals("NullPointerException",A.get(3));
        }catch (NullPointerException r){
            System.out.println(r.toString());
        }

        try {
            A.add(-1,"ArithmeticException");
            assertEquals("ArithmeticException",A.get(-1));
        }catch (ArithmeticException r){
            System.out.println(r.toString());
        }
    }

    @Test
    public void set() {
        SingleLinked A = new SingleLinked();
        A.add(0,"First");
        A.add(1,"second");
        A.add(2,"third");
        A.add(3,"fourth");
        A.add(4,"fifth");
        A.set(1,"New second");
        assertEquals("New second",A.get(1));
        try {
            A.set(-1,"ArithmeticException");
            assertEquals("ArithmeticException",A.get(-1));
        }catch (ArithmeticException r){
            System.out.println(r.toString());
        }
    }

    @Test
    public void remove() {
        SingleLinked A= new SingleLinked();
        A.add("My");       //index 0
        A.add("New");      //index 1
        A.add("Linked");   //index 2
        A.add("List");     //index 3
        A.add("which");    //index 4
        A.add("is");       //index 5
        A.add("Single");   //index 6
        assertEquals(7,A.size());
        assertEquals("New",A.get(1));
        //I will remove the second index
        A.remove(1);
        assertEquals(6,A.size());         // size decreased by 1
        assertEquals("Linked",A.get(1));   // the second removed and third index become the second
        try {
            A.remove(-1);
            assertEquals("ArithmeticException",A.get(-1));
        }catch (ArithmeticException r){
            System.out.println(r.toString());
        }
    }

    @Test
    public void sublist() {
        SingleLinked A= new SingleLinked();
        ILinkedList B;
        A.add("My");       //index 0
        A.add("New");      //index 1
        A.add("Linked");   //index 2
        A.add("List");     //index 3
        A.add("which");    //index 4
        A.add("is");       //index 5
        A.add("Single");   //index 6
        B=A.sublist(0,3);
        assertEquals(4,B.size()); //The list have four Elements from 0 to 3 inclusively
        // Now to make sure of what elements i take
        assertEquals("My",B.get(0));
        assertEquals("New",B.get(1));
        assertEquals("Linked",B.get(2));
        assertEquals("List",B.get(3));
        B=A.sublist(0,0);
        assertEquals(1,B.size()); //The list have one Element inclusively
        // Now to make sure of what element i take
        assertEquals("My",B.get(0));
        try {
            B= A.sublist(2,1);
            assertEquals("ArithmeticException",B.get(0));
        }catch (ArithmeticException r){
            System.out.println(r.toString());
        }
    }

    @Test
    public void contains() {
        SingleLinked A= new SingleLinked();
        A.add("My");       //index 0
        A.add("New");      //index 1
        A.add("Linked");   //index 2
        A.add("List");     //index 3
        A.add("which");    //index 4
        A.add("is");       //index 5
        A.add("Single");   //index 6
        assertTrue(A.contains("List"));
        assertFalse(A.contains("Not in List"));
    }
    @Test
    public void clear(){
        SingleLinked A= new SingleLinked();
        A.add("My");       //index 0
        A.add("New");      //index 1
        A.add("Linked");   //index 2
        A.add("List");     //index 3
        A.add("which");    //index 4
        A.add("is");       //index 5
        A.add("Single");   //index 6
        A.clear();
        assertTrue(A.isEmpty());
    }
}