import java.util.*;

public class Gen_H_Shuffel{

    static final int N = 1 << 20;  
    static final int REPETITIONS = 30;

    public static void main(String[] args) {

        String[] keys = new String[N];
        String[] values = new String[N];

       
        for (int i = 0; i < N; i++) {
            keys[i] = Integer.toString(i + 1);
        }

        List<String> list = Arrays.asList(keys);
        Collections.shuffle(list);
        list.toArray(keys);

        for (int i = 0; i < N; i++) {
            values[i] = Integer.toString(i + 1);
        }

        int[] loadPercent = {75, 80, 85, 90, 95};

        System.out.println("Load % | OpenHash(sec) | ChainedHash(sec)");

        for (int percent : loadPercent) {

            int used = (int) (N * (percent / 100.0));
            int m = N;

            double openTime = 0;
            double chainTime = 0;
            for (int r = 0; r < REPETITIONS; r++) {

                OpenedHash open = new OpenedHash(m);
                ChainedHash chain = new ChainedHash(m);

                for (int i = 0; i < used; i++) {
                    open.insert(keys[i], values[i]);
                    chain.insert(keys[i], values[i]); 
                }

                long start = System.currentTimeMillis();
                for (int i = 0; i < used; i++)
                    open.lookup(keys[i]);
                long end = System.currentTimeMillis();

                openTime += (end - start);

                start = System.currentTimeMillis();
                for (int i = 0; i < used; i++)
                    chain.lookup(keys[i]);
                end = System.currentTimeMillis();

                chainTime += (end - start);
            }
            System.out.printf("%d%%   | %.4f         | %.4f\n",
                    percent,
                    openTime / REPETITIONS / 1000.0,
                    chainTime / REPETITIONS / 1000.0);
    }
}
} 