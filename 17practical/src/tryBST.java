import java.util.LinkedList;
import java.util.Queue;

class tNode{
    public int key;
    public tNode left;
    public tNode right;

    public tNode(int key) {
        this.key = key;
        this.left = null;
        this.right = null;
    }
}
class BST{
    private tNode root;

    public BST() {
        this.root = null;
    }
    public void insert(int key) {
        root = insertRec(root, key);
    }
    private tNode insertRec(tNode node, int key) {
        if (node == null) return new tNode(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }
    public void removeEvens() {
        root = removeEvensRec(root);
    }
    private tNode removeEvensRec(tNode node) {
        if (node == null) return null;
        node.left = removeEvensRec(node.left);
        node.right = removeEvensRec(node.right);
        if (node.key % 2 == 0) {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            tNode minRight = findMin(node.right);
            node.key = minRight.key;
            node.right = deleteRec(node.right, minRight.key);
        }
        return node;
    }
    private tNode findMin(tNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
    private tNode deleteRec(tNode node, int key) {
        if (node == null) return null;
        if (key < node.key) node.left = deleteRec(node.left, key);
        else if (key > node.key) node.right = deleteRec(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            tNode minRight = findMin(node.right);
            node.key = minRight.key;
            node.right = deleteRec(node.right, minRight.key);
        }
        return node;
}
}

public class tryBST {
    static final int N = 20;
    static final int REPS = 30;

    static void populateBST(BST tree, int low, int high) {
        if (low > high) return;
        int mid = low + (high - low) / 2;
        tree.insert(mid);
        populateBST(tree, low, mid - 1);
        populateBST(tree, mid + 1, high);
    }

    static double timepopulate(BST tree, int maxKey) {
        tree.clear();
        long start = System.nanoTime();
        populateBST(tree, 1, maxKey);
        long end = System.nanoTime();
        return (end - start) / 1000000.0;
    }

    static double timeRemoveEvens(BST tree) {
        long start = System.nanoTime();
        tree.removeEvens();
        long end = System.nanoTime();
        return (end - start) / 1000000.0;
    }

    static double mean(double[] data) {
        double sum = 0;
        for (double v : data) sum += v;
        return sum / data.length;
    }

    static double stddev(double[] data, double avg) {
        double sumSq = 0;
        for (double v : data) sumSq += (v - avg) * (v - avg);
        return Math.sqrt(sumSq / data.length);
    }
}

    public static void main(String[] args) throws Exception {

       
    }
