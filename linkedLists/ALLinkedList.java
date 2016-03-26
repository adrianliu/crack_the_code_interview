package cc150.linkedLists;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by xiaopengliu on 26/03/16.
 */
public class ALLinkedList {
    private Node dummyHead;
    private int size;

    //construct an empty linkedlist
    public ALLinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public static void main(String[] args) {
        ALLinkedList list = new ALLinkedList();
        list.addLast(1);
        list.addLast(1);
        list.addLast(1);
        list.addLast(1);
        list.addLast(1);
        System.out.println(list.toString());
        list.removeDuplicates2();
        System.out.println(list.toString());

    }

    //Using Hashtable
    private void removeDuplicates1() {
        Hashtable<Integer, Integer> table = new Hashtable<>();
        Node pre = dummyHead;
        Node cur = dummyHead.next;
        while(cur != null) {
            if(table.containsKey(cur.val)) {
                Node nextNode = cur.next;
                pre.next = nextNode;
            } else {
                table.put(cur.val, 1);
                pre = cur;

            }
            cur = cur.next;
        }

    }

    //Not Using extra memory.
    private void removeDuplicates2() {
        Node pre = dummyHead;
        Node cur = dummyHead.next;
        while(cur != null) {
            Node checkNode = dummyHead.next;
            while(checkNode != cur) {
                if(checkNode.val == cur.val) {
                    //cur is duplicated.
                    Node nextNode = cur.next;
                    pre.next = nextNode;
                    cur = cur.next;
                    break;
                }
                checkNode = checkNode.next;

            }
            if(checkNode == cur) {
                pre = cur;
                cur = cur.next;
            }

        }
    }






    public void addFirst(int num) {
        Node newNode = new Node(num);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        size++;
    }

    public int removeFirst() {
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        int temp = dummyHead.next.val;
        dummyHead.next = dummyHead.next.next;
        size--;
        return temp;
    }

    public void addLast(int num) {
        Node newNode = new Node(num);
        Node last = dummyHead;
        while(last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        size++;
    }

    public int removeLast() {
        if(size == 0) {
            throw new IndexOutOfBoundsException();
        }
        Node pre = dummyHead;
        Node cur = dummyHead.next;
        while(cur.next != null) {
            pre = cur;
            cur = cur.next;
        }
        int temp = cur.val;
        pre.next = null;
        size--;
        return temp;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ALLinkedListIterator iter = new ALLinkedListIterator();
        while(iter.hasNext()) {
            sb.append(iter.next()+ " -> ");
        }
        sb.append("null");
        return sb.toString();
    }


    private static class Node{
        int val;
        Node next;

        public Node() {
            this.val = -1;
        }
        public Node(int val) {
            this.val = val;
        }
    }

    private class ALLinkedListIterator implements Iterator<Integer>{
        public void remove() {
            throw new UnsupportedOperationException();
        }
        private Node nextNode;
        public ALLinkedListIterator() {
            nextNode = dummyHead.next;
        }
        public boolean hasNext() {
            return nextNode != null;
        }

        public Integer next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            int temp = nextNode.val;
            nextNode = nextNode.next;
            return temp;
        }

    }




}
