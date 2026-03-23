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


public class app{
    public static void main(String[] args) throws Exception {

       
    }
}
