package tpo.task_2;

public class HashTableNode {

    private int value;
    private HashTableNode next_node;

    public HashTableNode(int value, HashTableNode next_node) {
        this.value = value;
        this.next_node = next_node;
    }

    public int getValue() {
        return value;
    }

    public HashTableNode getNext_node() {
        return next_node;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext_node(HashTableNode next_node) {
        this.next_node = next_node;
    }

}
