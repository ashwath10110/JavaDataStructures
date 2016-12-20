package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class BTree {

    node root;

    public BTree() {
        this.root = null;
    }

    public BTree(node root) {
        this.root = root;
    }

    public boolean insert(int value) {

        if (root == null) {
            root = new node(value);
            return true;
        } else {
            return root.insert(value);
        }
    }

    public boolean search(int value) {
        if (root == null) {
            return false;
        } else {
            return root.search(value);
        }
    }

    public void minimumValue() {

        if (root == null) {
            System.out.print("Tree Null");
            return;
        } else {

            node current = root;

            while (current.left != null) {
                current = current.left;
            }

            System.out.print(current.value);
        }
    }

    public boolean isValidBST() {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isValidBSTHelper(node current, int min, int max) {

        if (current == null) {
            return true;
        }

        if (current.value > min
                && current.value < max
                && isValidBSTHelper(current.left, min, current.value)
                && isValidBSTHelper(current.right, current.value, max)) {
            return true;
        } else {
            return false;
        }
    }

    public int getHeightOfTree(node current) {

        if (current == null) {
            return 0;
        } else {
            int lheight = getHeightOfTree(current.left);
            int rheight = getHeightOfTree(current.right);

            return lheight > rheight ? lheight : rheight;
        }
    }

    public void printLevelOrderRecursion() {

        int heightOfTree = getHeightOfTree(root);

        for (int i = 0; i < heightOfTree; i++) {
            printLevelOrderRecursionHelper(root, i);
        }
    }

    public void printLevelOrderRecursionHelper(node current, int level) {

        if (current == null) {
            return;
        }
        if (level == 1) {
            System.out.print(current.value + " ");

        } else if (level > 1) {
            printLevelOrderRecursionHelper(current.left, level - 1);
            printLevelOrderRecursionHelper(current.right, level - 1);
        }
    }

    public void levelOrderQueue() {

        Queue<node> Q = new LinkedList<node>();

        Q.add(root);

        while (!Q.isEmpty()) {

            node temp = Q.poll();
            System.out.print(temp.value + " ");

            if (temp.left != null) {
                Q.add(temp.left);
            }
            if (temp.right != null) {
                Q.add(temp.right);
            }
        }
    }

    public void levelOrderQueueLineByLine() {

        Queue<node> Q = new LinkedList<node>();

        Q.add(root);

        while (true) {

            int nodeCount = Q.size();

            if (nodeCount == 0) {
                break;
            }

            while (nodeCount > 0) {

                node temp = Q.poll();

                System.out.print(temp.value + " ");

                if (temp.left != null) {
                    Q.add(temp.left);
                }

                if (temp.right != null) {
                    Q.add(temp.right);
                }
                nodeCount--;
            }

            System.out.println();
        }
    }

    public void levelOrderQueueZigZag() {

        ArrayList<Integer> al = new ArrayList<>();

        boolean evenLevel = true;

        Queue<node> Q = new LinkedList<node>();

        Q.add(root);

        while (true) {

            int nodeCount = Q.size();

            al.clear();

            if (nodeCount == 0) {
                break;
            }

            while (nodeCount > 0) {

                node temp = Q.poll();

                al.add(temp.value);

//                System.out.print(temp.value + " ");

                if (temp.left != null) {
                    Q.add(temp.left);
                }

                if (temp.right != null) {
                    Q.add(temp.right);
                }
                nodeCount--;
            }

            if (evenLevel) {
                System.out.print(al);
                evenLevel = !evenLevel;
            } else {
                Collections.reverse(al);
                System.out.print(al);
                evenLevel = !evenLevel;
            }

            System.out.println();
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

}

class node {

    node left;
    node right;
    int value;

    public node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public boolean insert(int value) {

        if (this.value == value) {
            return false;
        } else {

            if (value < this.value) {

                if (this.left == null) {
                    this.left = new node(value);
                    return true;
                } else {
                    return this.left.insert(value);
                }
            } else {

                if (this.right == null) {
                    this.right = new node(value);
                    return true;
                } else {
                    return this.right.insert(value);
                }
            }
        }
    }

    public boolean search(int value) {

        if (this.value == value) {
            return true;
        } else {

            if (value < this.value) {

                if (this.left == null) {
                    return false;
                } else {
                    return this.left.search(value);
                }
            } else {

                if (this.right == null) {
                    return false;
                } else {
                    return this.right.search(value);
                }
            }
        }
    }

}
