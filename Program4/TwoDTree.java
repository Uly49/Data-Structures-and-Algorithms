/*
 * Ulysses Palomar
 * 4/5/2023
 * University of Wisconsin - Parkside
 * CSCI 340 - Data Structures and Algorithms
 * Professor Vijayalakshmi (Viji) Ramasamy
 * Homework - Program 4
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * NOTE: THIS CLASS WORKS WITH THE PROVIDED Test2DTree.java CLASS
 */

import java.awt.Point;
import java.util.ArrayList;

public class TwoDTree {

    // define an interface for the nodes of the tree
    private static interface TwoDTreeNode {
        boolean isLeaf(); // check if the node is a leaf

        TwoDTreeNode insert(Point p, boolean splitByX); // insert a new point into the tree

        boolean search(Point p); // search for a point in the tree

        ArrayList<Point> searchRange(Point p1, Point p2, boolean splitByX); // search for points in a given range
    }

    // define a class for the internal nodes of the tree
    private static class TwoDTreeNodeImpl implements TwoDTreeNode {

        private Point p; // the point stored in the node
        private TwoDTreeNode left, right; // pointers to the left and right subtrees
        private boolean splitByX; // flag indicating if the node was split by the x-coordinate

        public TwoDTreeNodeImpl(Point p, boolean splitByX) {
            this.p = p;
            this.splitByX = splitByX;
        }

        // check if the node is a leaf
        public boolean isLeaf() {
            return left == null && right == null;
        }

        // insert a new point into the tree
        public TwoDTreeNode insert(Point q, boolean splitByX) {
            if (splitByX) { // split by the x-coordinate
                if (q.x <= p.x) { // insert into the left subtree
                    if (left == null) {
                        left = new TwoDTreeNodeImpl(q, !splitByX); // create a new leaf node
                    } else {
                        left = left.insert(q, !splitByX); // recursively insert into the left subtree
                    }
                } else { // insert into the right subtree
                    if (right == null) {
                        right = new TwoDTreeNodeImpl(q, !splitByX); // create a new leaf node
                    } else {
                        right = right.insert(q, !splitByX); // recursively insert into the right subtree
                    }
                }
            } else { // split by the y-coordinate
                if (q.y <= p.y) { // insert into the left subtree
                    if (left == null) {
                        left = new TwoDTreeNodeImpl(q, !splitByX); // create a new leaf node
                    } else {
                        left = left.insert(q, !splitByX); // recursively insert into the left subtree
                    }
                } else { // insert into the right subtree
                    if (right == null) {
                        right = new TwoDTreeNodeImpl(q, !splitByX); // create a new leaf node
                    } else {
                        right = right.insert(q, !splitByX); // recursively insert into the right subtree
                    }
                }
            }
            return this; // return the modified node
        }

        // search for a point in the tree
        public boolean search(Point q) {
            if (p.equals(q)) { // the point is found
                return true;
            } else if (splitByX ? q.x <= p.x : q.y <= p.y) { // search the left subtree
                return left != null && left.search(q);
            } else { // search the right subtree
                return right != null && right.search(q);
            }
        }

        // search for points in a given range
        public ArrayList<Point> searchRange(Point p1, Point p2, boolean splitByX) {
            ArrayList<Point> result = new ArrayList<Point>();
            if (p.x >= p1.x && p.x <= p2.x && p.y >= p1.y && p.y <= p2.y) { // check if the point is inside the range
                result.add(p); // add the point to the result list
            }
            if (left != null && (splitByX ? p.x >= p1.x : p.y >= p1.y)) { // if there is a left subtree, and it's
                                                                          // possible to have points in the range
                result.addAll(left.searchRange(p1, p2, !splitByX)); // recursively search in the left subtree
            }
            if (right != null && (splitByX ? p.x <= p2.x : p.y <= p2.y)) { // if there is a right subtree, and it's
                                                                           // possible to have points in the range
                result.addAll(right.searchRange(p1, p2, !splitByX)); // recursively search in the right subtree
            }
            return result; // return the result list
        }
    }

    private TwoDTreeNode root;

    // Constructor to initialize an empty tree
    public TwoDTree() {
        root = null;
    }

    // Method to insert a point into the tree
    public void insert(Point p) {
        if (root == null) {
            root = new TwoDTreeNodeImpl(p, true);
        } else {
            root.insert(p, true);
        }
    }

    // Method to search for a point in the tree
    public boolean search(Point p) {
        return root != null && root.search(p);
    }

    // Method to search for all points within a given range
    public ArrayList<Point> searchRange(Point p1, Point p2) {
        if (root == null) {
            // If the tree is empty, return an empty list
            return new ArrayList<Point>();
        }
        // Otherwise, start the search from the root and return the results
        return root.searchRange(p1, p2, true);
    }
}