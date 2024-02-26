package tpo.task_2;

import java.util.ArrayList;

public class HashTableInteger {
    private final HashTableNode[] array;
    private final int array_size;

    public HashTableInteger(int init) {
        array_size = init;
        array = new HashTableNode[init];
    }

    public void add_element(int value) {
        if (array[getHash(value)] == null) {
            array[getHash(value)] = new HashTableNode(value, null);
        } else {
            array[getHash(value)] = new HashTableNode(value, array[getHash(value)]);
        }
    }

    public boolean find_element(int value) {
        HashTableNode cur_node = array[getHash(value)];
        boolean is_exist = false;
        while (cur_node != null) {
            if (cur_node.getValue() == value) {
                is_exist = true;
                break;
            }
            cur_node = cur_node.getNext_node();
        }
        return is_exist;
    }

    public boolean delete_element(int value) {
        HashTableNode cur_node = array[getHash(value)];
        HashTableNode prev_node = null;
        boolean is_exist = false;
        while (cur_node != null) {
            if (cur_node.getValue() == value) {
                is_exist = true;
                if (prev_node != null) {
                    prev_node.setNext_node(cur_node.getNext_node());
                } else {
                    array[getHash(value)] = cur_node.getNext_node();
                }
                break;
            }
            prev_node = cur_node;
            cur_node = cur_node.getNext_node();
        }
        return is_exist;
    }

    public Integer[] getAsArray() {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < array_size; i++) {
            if (array[i] == null) {
                result.add(null);
            } else {
                HashTableNode cur_node = array[i];
                while (cur_node != null) {
                    result.add(cur_node.getValue());
                    cur_node = cur_node.getNext_node();
                }
            }
        }
        return result.toArray(new Integer[0]);
    }

    private int getHash(int value) {
        return value % array_size;
    }

}
