package com.company;

public class Main {

    public static void main(String[] args) {


        System.out.println("End of Execution!!!");

        node root = new node(5);
        root.left = new node(10);
        root.right = new node(15);
        root.left.left = new node(20);
        root.left.right = new node(25);
        root.right.left = new node(30);
        root.right.right = new node(35);
        root.left.left.left = new node(40);
        root.left.left.right = new node(45);

        BTree b = new BTree(root);

        b.levelOrderQueueZigZag();


        System.out.println(" Spiral Print of a Tree : ");
        b.levelOrderQueueLineByLine();

    }
}
