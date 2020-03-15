package eg.edu.alexu.csd.datastructure.linkedList;

public class DoubleList implements ILinkedList {
    int size =0 ;
    DNode head ;



    public DoubleList() {
        head=new DNode();
    }

    @Override
    public void add(int index, Object element) {
        if (index==size)
            add(element);
        checkIndex(index);
        DNode temp=new DNode() ;
        if (index==0){
            temp.next=head;
            head.pervious=temp;
            head=temp;
            head.setElement(element);
        }
        DNode before =getNode(index-1);
        DNode after = getNode(index);
        temp.pervious = before;
        temp.next = after;
        temp.setElement(element);
    }

    @Override
    public void add(Object element) {
        size++;
        if(isEmpty())
            head.setElement(element);
        else {
            DNode temp;
            temp=head;
            while(temp.getNext()!=null){
                temp=temp.getNext();
            }
            temp.setNext(new DNode());
            temp.getNext().setElement(element);
        }
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return getNode(index).getElement();
    }

    @Override
    public void set(int index, Object element) {
        checkIndex(index);
        getNode(index).setElement(element);;
    }

    @Override
    public void clear() {
        size=0;
        if(isEmpty())
            return;
        head.setNext(null);
        head.setElement(null);
    }

    @Override
    public boolean isEmpty() {
        if(head.getElement()==null)
            return true;
        return false;    }

    @Override
    public void remove(int index) {
        checkIndex(index);
        if (index==0)
            head= head.getNext();
        else if (index==size-1) {
            DNode Temp =getNode(index-1);
            Temp.next =null;
        }
        else {
            DNode after = getNode(index + 1);
            DNode before = getNode(index - 1);
            before.next = after;
            after.pervious = before;
        }
        size--;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex);
        if (fromIndex>toIndex)
            throw new  ArithmeticException("Wrong interval");
        DoubleList newList = new DoubleList();
        for (int i= fromIndex; i<=toIndex;i++){
            newList.add(get(i));
        }
        return newList;
    }

    @Override
    public boolean contains(Object o) {
        DNode temp=head;
        while(temp.getNext()!=null){
            if(temp.getElement().equals(o))
                return true;
            temp=temp.getNext();
        }
        return false;
    }

    public void checkIndex(int index)  {
        if(index<0)
            throw new ArithmeticException("Index is negative.");
        if(index>size-1)
            throw new NullPointerException("Index out of bounds");
    }

    private DNode getNode(int index){
        int i=0;
        DNode temp=head;
        while(i!=index){
            i++;
            temp=temp.getNext();
        }
        return temp;
    }

    @Override
    public String toString() {
        String list="{ ";
        if(isEmpty())
            throw new NullPointerException("List is empty .");
        DoubleList.DNode temp=head;
        while(temp!=null){
            list+=temp.getElement().toString();
            list+=" ,";
            temp=temp.getNext();
        }
        list+=" }";
        return list;
    }


    class DNode{//The list's element.

        private Object element;
        private DNode  next ,pervious;


        public void setElement(Object element) {
            this.element = element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public Object getElement() {
            return element;
        }

        public DNode getNext() {
            return next;
        }
        public void setPervious (DNode pervious){
            this.pervious = pervious;
        }

        public DNode getPervious (){
            return pervious;
        }
    }
}
