import java.util.*;

class Node {
    int key, height, size;
    Node left, right;

    Node(int key) {
        this.key = key;
        height = 1;
        size = 1;
    }
}

public class RankAVL {

    static int height(Node n) {
        return n == null ? 0 : n.height;
    }

    static int size(Node n) {
        return n == null ? 0 : n.size;
    }

    static void update(Node n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    static int balance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static Node rightRotate(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        update(y);
        update(x);

        return x;
    }

    static Node leftRotate(Node x) {
        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        update(x);
        update(y);

        return y;
    }

    static Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);

        update(node);

        int bf = balance(node);

        if (bf > 1 && key < node.left.key)
            return rightRotate(node);

        if (bf < -1 && key > node.right.key)
            return leftRotate(node);

        if (bf > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (bf < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.key + "[" + root.size + "] ");
        inorder(root.right);
    }

    public static void main(String[] args) {

        int arr[] = {820,540,910,770,880,460,990,600,730,950,510};

        Node root = null;

        for(int x : arr)
            root = insert(root,x);

        inorder(root);
    }
}
