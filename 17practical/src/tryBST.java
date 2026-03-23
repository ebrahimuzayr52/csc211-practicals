public class tryBST {
    private static final int N = 20;
    private static final int REPS = 30;

    private static void populateBST(BST tree, int lo, int hi) {
        if (lo > hi) return;
        int mid = lo + (hi - lo) / 2;
        tree.insert(mid);
        populateBST(tree, lo, mid - 1);
        populateBST(tree, mid + 1, hi);
    }

    private static double timePopulate(BST tree, int maxKey) {
        tree.clear();
        long start = System.nanoTime();
        populateBST(tree, 1, maxKey);
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;   // convert nanoseconds → milliseconds
    }
    private static double timeRemoveEvens(BST tree) {
        long start = System.nanoTime();
        tree.removeEvens();
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }
    private static double mean(double[] data) {
        double sum = 0;
        for (double v : data) sum += v;
        return sum / data.length;
    }

    private static double stddev(double[] data, double avg) {
        double sumSq = 0;
        for (double v : data) sumSq += (v - avg) * (v - avg);
        return Math.sqrt(sumSq / data.length);   // population std-dev
    }


}

public class App {
    public static void main(String[] args) throws Exception {

       
    }
}
