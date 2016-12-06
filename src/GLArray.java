class EndOfGLArray extends Exception {};

/**
 * Created by Bram on 04/11/2016.
 */
public class GLArray {
    /**
     * testing
     * @param args
     */
    public static void main(String[] args) {
        //make and populate arrays
        Node testNode = new Node(1);
        add(testNode, 2);
        add(testNode, 5);
        add(testNode, -1);
        add(testNode, 8);
        add(testNode, 33);
        try {
            System.out.println(testNode.toString());
            System.out.println("length 1 : " + length(testNode));
            System.out.println("index 0: " + get(testNode, 0));
            System.out.println("index 1: " + get(testNode, 1));
            System.out.println("index 2: " + get(testNode, 2));
            System.out.println("index 3: " + get(testNode, 3));
            System.out.println("index 4: " + get(testNode, 4));
            System.out.println("array sizes check:");
            System.out.println("length 1, should be 1: " + testNode.getSize());
            System.out.println("length 2, should be 2: " + testNode.getNextNode().getSize());
            System.out.println("length 3, should be 4: " + testNode.getNextNode().getNextNode().getSize());
            System.out.println("add 4 values, new array made");
            add(testNode, 2);
            add(testNode, 11);
            add(testNode, 7);
            add(testNode, 75);
            System.out.println("length 3, should be 8: " + testNode.getNextNode().getNextNode().getNextNode().getSize());
            System.out.println("values in all arrays: ");
            System.out.println(testNode.toString());
            System.out.println(testNode.getNextNode().toString());
            System.out.println(testNode.getNextNode().getNextNode().toString());
            System.out.println(testNode.getNextNode().getNextNode().getNextNode().toString());
            System.out.println();
            System.out.println("index 5: " + get(testNode, 5));
            System.out.println("index 6: " + get(testNode, 6));
            System.out.println("index 7: " + get(testNode, 7));
            System.out.println("index 8: " + get(testNode, 8));
            System.out.println("final length: " + length(testNode));
            System.out.println("erroneous get index 10: ");
            System.out.println(get(testNode, 10));
        }
        catch(EndOfGLArray e){
            System.out.println(e.fillInStackTrace());
        }
    }

    /**
    length: takes in a node and returns the length of the GLArray. This is
    the length of the actual elements that have been added to the array, not
    including any additional space that has been allocated, but not yet used.
     */
    public static int length(Node nodeIn){
        boolean endReached = false;
        int tally = 0;
        do{
            if(nodeIn.getNextNode() == null){
                endReached = true;
                for (Integer i:nodeIn.getArray()) {
                    if(i != null){
                        tally++;
                    }
                }
            }else{
                tally+= nodeIn.getElementsInUse();
                nodeIn = nodeIn.getNextNode();
            }
        }
        while(!endReached);
        return tally;
    }
    /**
    get: takes in a node and an int index, and returns the value in the GLArray
    corresponding to that index. In the example GLArray above, calling
    get with index 0 should return 2, with index 1 should return 5, with index
    2 should return -1, with index 4 should return 33, and with any higher
    index should throw a suitable exception.
     */
    public static int get(Node nodeIn, Integer index) throws EndOfGLArray{
        if ((index) < nodeIn.getElementsInUse()) {
            return  nodeIn.getArray()[index];
        } else {
            if(nodeIn.getNextNode() == null)
                throw new EndOfGLArray();

            index -= nodeIn.getElementsInUse();
            nodeIn = nodeIn.getNextNode();
            return get(nodeIn, index);
        }
    }
    /**
    add: takes in a node and a Integer and adds it to the end of the GLArray,
    similar to adding to an ArrayList. Hint: if the last Java array is full,
    allocate (with new) another node and link it on to the end of the GLArray.
     */
    public static void add(Node nodeIn, Integer intIn){
        if(nodeIn.getNextNode() == null){
            //check if full
            if(nodeIn.getElementsInUse() == nodeIn.getSize()){
                nodeIn.setNextNode(new Node(nodeIn.getSize() * 2));
                nodeIn.getNextNode().addValue(intIn);
            }else{
                nodeIn.addValue(intIn);
            }
        }else{
            nodeIn = nodeIn.getNextNode();
            add(nodeIn, intIn);
        }
    }
}