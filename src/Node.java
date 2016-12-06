import java.util.Arrays;

/**
 * Created by Bram on 04/11/2016.
 */
public class Node {

    private Node nextNode;//next node in the list
    private Integer[] intArray;//array of elements in the node
    private int elementsInUse;//int of elements currently in use

    public Node(int sizeIn){
        elementsInUse = 0;
        nextNode = null;
        intArray = new Integer[sizeIn];

    }

    public String toString(){
        return Arrays.deepToString(intArray);
    }

    public int getElementsInUse(){
        return elementsInUse;
    }

    public Node getNextNode(){
        return nextNode;
    }

    public Integer[] getArray(){
        return intArray;
    }

    public int getSize() { return intArray.length; }

    public void setNextNode(Node nextNodeIn){
        assert nextNode == null: ("nextNode is not null");
        nextNode = nextNodeIn;
    }

    public void addValue(int valueIn){
        assert nextNode == null: ("nextNode is not null");
        assert elementsInUse < intArray.length: ("array is full");
        assert intArray[elementsInUse] == null: ("value is not null: " + intArray[elementsInUse] );
        intArray[elementsInUse] = valueIn;
        elementsInUse++;
    }
}
