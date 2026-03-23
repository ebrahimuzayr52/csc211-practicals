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
    public void clear() {
    root = null;
}
    public int size() { return sizeRec(root); }
    private int sizeRec(tNode node) {
        if (node == null) return 0;
        return 1 + sizeRec(node.left) + sizeRec(node.right);
}

    public int height() { return heightRec(root); }
    private int heightRec(tNode node) {
        if (node == null) return 0;
        return 1 + Math.max(heightRec(node.left), heightRec(node.right));
}
    public boolean isBST() { return isBSTRec(root, Integer.MIN_VALUE, Integer.MAX_VALUE); }
    private boolean isBSTRec(tNode node, int min, int max) {
        if (node == null) return true;
        if (node.key <= min || node.key >= max) return false;
        return isBSTRec(node.left, min, node.key) && isBSTRec(node.right, node.key, max);
}
    public void inOrder() { inOrderRec(root); System.out.println(); }
    private void inOrderRec(tNode node) {
        if (node == null) return;
        inOrderRec(node.left);
        System.out.print(node.key + " ");
        inOrderRec(node.right);
    }
    public void breadthFirst() {
        if (root == null) return;
        Queue<tNode> q = new LinkedList<>();
        q.add(root);
            while (!q.isEmpty()) {
            tNode cur = q.poll();
            System.out.print(cur.key + " ");
            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
    }
        System.out.println();
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
    static final int N = 24;
    static final int REPS = 30;

    static void populateBST(BST tree, int low, int high) {
        if (low > high) return;
        int mid = low + (high - low) / 2;
        tree.insert(mid);
        populateBST(tree, low, mid - 1);
        populateBST(tree, mid + 1, high);
    }

    static double time_populate(BST tree, int maxKey) {
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

    public static void main(String[] args){
        int maxKey = (1 << N) - 1;

        System.out.println("Testing with small tree first (keys 1..15)");
        BST small = new BST();
        populateBST(small, 1, 15);
        System.out.println();
        System.out.println("Running timing with n=" + N + " maxKey=" + maxKey);

        BST tree = new BST();
        double[] popTimes = new double[REPS];
        double[] remTimes = new double[REPS];

        for (int i = 0; i < REPS; i++) {
            popTimes[i] = time_populate(tree, maxKey);
            remTimes[i] = timeRemoveEvens(tree);
        }

        double popMean = mean(popTimes);
        double popSD = stddev(popTimes, popMean);
        double remMean = mean(remTimes);
        double remSD = stddev(remTimes, remMean);

        System.out.println();
        System.out.printf("%-30s %15s %15s%n", "Method", "Avg time (ms)", "Std Deviation");
        System.out.println("");
        System.out.printf("%-30s %15.2f %15.2f%n", "Populate tree", popMean, popSD);
        System.out.printf("%-30s %15.2f %15.2f%n", "Remove evens from tree", remMean, remSD);

    }
}
