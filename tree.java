package com.company;

import java.util.Queue;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        node tree = new node(1);
        tree.left = new node(2);
        tree.right = new node(3);
        tree.left.left = new node(4);
        tree.left.right = new node(5);
//        tree.right.left = new node(1);
//        tree.right.right = new node(30);

        inorder(tree);
        System.out.println("\n\n");
        processTree(tree);
        inorder(tree);
    }

    public static void processTree(node current){

        int leftData = 0;
        int rightData = 0;
        int diff = 0;

        if (current == null || (current.left == null && current.right == null)){
            return;
        }
        else{

            processTree(current.left);
            processTree(current.right);

            if (current.left != null){
                leftData = current.left.value;
            }

            if (current.right != null){
                rightData = current.right.value;
            }

            diff =  (leftData + rightData) - current.value ;

            if (diff > 0){
                current.value += diff;
            }

            if (diff < 0){
                increment(current, -diff);
            }
        }
    }

    public static void increment(node current, int diff){

        if (current.left != null){
            current.left.value += diff;
            increment(current.left,diff);
        }else if (current.right != null){
            current.right.value += diff;
            increment(current.right,diff);
        }
    }

    public static void inorder(node current){

        if (current == null){
            return;
        }

        inorder(current.left);
        System.out.println(current.value);
        inorder(current.right);
    }

    public static void postorder(node current){

        if (current == null){
            return;
        }

        postorder(current.left);
        postorder(current.right);
        System.out.println(current.value);
    }

    public static node mirror(node current){

        if (current == null){
            return current;
        }
        node left = mirror(current.left);
        node right = mirror(current.right);

        current.left = right;
        current.right = left;

        return current;
    }

    public static boolean identicalTrees(node tree1,node tree2){

        if (tree1 == null && tree2 == null){
            return true;
        }

        if (tree1 != null && tree2 != null){
            return (tree1.value == tree2.value && identicalTrees(tree1.left,tree2.left) && identicalTrees(tree1.right,tree2.right));
        }
        return false;
    }

    public static int maxDepth(node current){

        if (current == null){
            return 0;
        }
        else{
            int leftBranchDepth = maxDepth(current.left);
            int rightBranchDepth = maxDepth(current.right);

            return leftBranchDepth > rightBranchDepth ? leftBranchDepth+1: rightBranchDepth+1;
        }
    }










}


class node{

    node left;
    node right;
    int value;

    public node(node left, node right, int value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public node(int val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }

    public boolean insert(int value){

        if (value == this.value){
            return false;
        }
        else if (value < this.value){
            if (this.left == null){
                this.left = new node(value);
                return true;
            }else{
                return this.left.insert(value);
            }
        }else if (value > this.value){
            if (this.right == null){
                this.right = new node(value);
                return true;
            }else{
                return this.right.insert(value);
            }
        }

        return false;
    }

}

class BTree{

    node root;

    public BTree() {
        this.root = null;
    }

    public boolean addNode(int value){
        if (root == null){
            root = new node(value);
            return true;
        }
        else{
            return root.insert(value);
        }
    }

    public void printLeaves(){
        printLeafNodes(this.root);
    }

    public void printLeafNodes(node t)
    {
        if(t == null)
            return;
        if(t.left == null && t.right==null)
            System.out.println(t.value);

        printLeafNodes(t.left);

        printLeafNodes(t.right);
    }




}
