package org.vincent.binarysearch;

/**
 * @author PengRong
 * @package org.vincent.binarysearch
 * @ClassName Node.java
 * @date 2019/6/22 - 18:50
 * @ProjectName JavaAopLearning
 * @Description: TODO
 */

public class Node {
    int key;
    int value;
    Node leftChild;
    Node rightChild;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Node{");
        sb.append("key=").append(key);
        sb.append(", value=").append(value);
        sb.append(", leftChild=").append(leftChild);
        sb.append(", rightChild=").append(rightChild);
        sb.append('}');
        return sb.toString();
    }
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.leftChild = null;
        this.rightChild = null;
    }

    public Node(int key, int value, Node leftChild, Node rightChild) {
        super();
        this.key = key;
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public Node() {

    }
}
