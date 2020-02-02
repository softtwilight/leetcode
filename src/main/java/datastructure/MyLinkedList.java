package datastructure;

import java.util.LinkedList;

/**
 * @author softtwilight
 * @vesion create on 2018/11/16
 */
public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size = 0;


    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.setNext(tail);
        tail.setPrev(head);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (size == 0 || index < 0 || index >= size){
            return -1;
        }
        Node node = findNode(index);
        return node.getV();
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
        size++;

    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;
        node.next = tail;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size){
            return;
        }
        if (index == 0){
            addAtHead(val);
            return;
        }
        if (index == size){
            addAtTail(val);
            return;
        }

        Node newNode = new Node(val);
        Node node = findNode(index);
        node.prev.next = newNode;
        newNode.prev = node.prev;
        node.prev = newNode;
        newNode.next = node;
        size++;

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size){
            return;
        }
        Node node = findNode(index);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        size--;
    }

    private Node findNode(int index) {
        Node node = head;
        if (index <= size / 2){
            for (int i = 0; i <= index; i++){
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = 0; i < size - index; i++){
                node = node.prev;
            }
        }
        return node;
    }


    private static class Node {
        private Node prev = null;
        private Node next = null;
        private int v;

        public Node(int v){
            this.v = v;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getV() {
            return v;
        }
    }
}

