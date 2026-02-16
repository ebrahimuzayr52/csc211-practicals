import java.util.Random;

public class mcs_test {

    public static long mcsOn3(int[] X) {
        int n = X.length;
        long count = 0;
        int max = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low; high < n; high++) {
                int sum = 0;
                for (int r = low; r < high; r++) {
                    sum += X[r];
                    count++;
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return count;
    }

    public static long mcsOn2A(int[] X) {
        int n = X.length;
        long count = 0;
        int max = 0;

        for (int low = 0; low < n; low++) {
            int sum = 0;
            for (int r = low; r < n; r++) {
                sum += X[r];
                count++;
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return count;
    }

    public static long mcsOn2B(int[] X) {
        int n = X.length;
        long count = 0;
        int[] sumTo = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sumTo[i] = sumTo[i - 1] + X[i - 1];
            count++;
        }

        int max = 0;

        for (int low = 0; low < n; low++) {
            for (int high = low + 1; high <= n; high++) {
                int sum = sumTo[high] - sumTo[low];
                count++;
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return count;
    }

    public static long mcsOn(int[] X) {
        int n = X.length;
        long count = 0;
        int maxSoFar = 0;
        int maxToHere = 0;

        for (int i = 0; i < n; i++) {
            maxToHere = Math.max(maxToHere + X[i], 0);
            maxSoFar = Math.max(maxSoFar, maxToHere);
            count++;
        }
        return count;
    }

    public static int[] makeArray(int n) {
        Random r = new Random();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int val = r.nextInt(n) + 1;
            if (r.nextInt(3) == 0) {
                val = -val;
            }
            arr[i] = val;
        }
        return arr;
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 10000, 100000, 1000000};

        System.out.printf("%-10s %-15s %-15s %-15s %-15s\n",
                "n", "O(n^3)", "O(n^2)", "O(n^2)", "O(n)");

        for (int n : sizes) {

            int[] X = makeArray(n);

            long c3 = -1;
            long c2a = -1;
            long c2b = -1;
            long c1;

            if (n <= 10000) {
                c3 = mcsOn3(X);
            }

            if (n <= 100000) {
                c2a = mcsOn2A(X);
                c2b = mcsOn2B(X);
            }

            c1 = mcsOn(X);

            System.out.printf("%-10d %-15d %-15d %-15d %-15d\n",
                    n, c3, c2a, c2b, c1);
        }
    }
}
