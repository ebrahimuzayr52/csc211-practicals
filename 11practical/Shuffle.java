import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;

public class Shuffle {
    private static Random rand = new Random();

    public static int[] slowShuffle(int N) {
        int[] shuffled = new int[N];
        boolean[] notPresent = new boolean[N + 1];
        Arrays.fill(notPresent, true);
        int index = 0;

        while (index < N - 1) {
            int r = rand.nextInt(N) + 1;
            if (notPresent[r]) {
                shuffled[index] = r;
                notPresent[r] = false;
                index++;
            }
        }

        for (int r = 1; r <= N; r++) {
            if (notPresent[r]) {
                shuffled[N - 1] = r;
                break;
            }
        }
        return shuffled;
    }

    public static int[] biasedShuffle(int N) {
        int[] shuffled = new int[N];
        for (int i = 0; i < N; i++) {
            shuffled[i] = i + 1;
        }

        for (int i = 0; i < N; i++) {
            int r = rand.nextInt(N);
            int temp = shuffled[i];
            shuffled[i] = shuffled[r];
            shuffled[r] = temp;
        }
        return shuffled;
    }

    public static int[] shuffle(int N) {
        int[] B = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = i + 1;
        }

        for (int b = 0; b < N; b++) {
            int r = b + rand.nextInt(N - b);
            int temp = B[b];
            B[b] = B[r];
            B[r] = temp;
        }
        return B;
    }

    public static void runTest(String label, boolean biased) {
        HashMap<String, Integer> D = new HashMap<>();
        int N = 3;
        int runs = 60000;

        for (int i = 0; i < runs; i++) {
            int[] res = biased ? biasedShuffle(N) : shuffle(N);
            String key = "";
            for (int val : res) key += val;

            D.put(key, D.getOrDefault(key, 0) + 1);
        }

        System.out.println("\nResults for " + label + ":");
        for (String key : D.keySet()) {
            System.out.println(key + ": " + D.get(key));
        }
    }

    public static void main(String[] args) {
        runTest("Biased Method", true);
        runTest("Unbiased Method", false);
    }
}