import java.io.*;
import java.text.DecimalFormat;
import java.util.Random;

public class test {
   public static final int N = 32655; 
   public static final int REPS = 30; 


   public static class Node {
      public int key;
      public String data;

      public Node(int key, String data) {
         this.key = key;
         this.data = data;
      }
   }

   public static void main(String[] args) {
      Node[] records = new Node[N];

   
      String dataPath = "13practical/src/ulysses.numbered";
      File f = new File(dataPath);
      if (f.exists()) {
         try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int idx = 0;
            while ((line = br.readLine()) != null && idx < N) {
               line = line.trim();
               if (line.isEmpty()) continue;
               
               String[] parts = line.split("\\s+", 2);
               int key = 0;
               String rest = "";
               try {
                  key = Integer.parseInt(parts[0]);
                  if (parts.length > 1) rest = parts[1];
               } catch (NumberFormatException e) {
                  
                  rest = line;
               }
               records[idx++] = new Node(key, rest);
            }
            for (int j = 0; j < N; j++) {
               if (records[j] == null) records[j] = new Node(j + 1, "");
            }
         } catch (IOException e) {
            System.err.println("Error reading " + dataPath + ": " + e.getMessage());
            for (int j = 0; j < N; j++) records[j] = new Node(j + 1, "");
         }
      } else {
        
         for (int j = 0; j < N; j++) records[j] = new Node(j + 1, "");
      }

     
      Random rand = new Random();

      long[] linearTimes = new long[REPS];
      long[] binaryTimes = new long[REPS];

      for (int i = 0; i < REPS; i++) {
         int key = 1 + rand.nextInt(N - 1); 

         long t0 = System.nanoTime();
         linearsearch(records, key);
         long t1 = System.nanoTime();
         linearTimes[i] = t1 - t0;

         t0 = System.nanoTime();
         binarysearch(records, key);
         t1 = System.nanoTime();
         binaryTimes[i] = t1 - t0;
      }

      
      double linear_avg = average(linearTimes);
      double linear_std = stddev(linearTimes, linear_avg);
      double binary_avg = average(binaryTimes);
      double binary_std = stddev(binaryTimes, binary_avg);

      DecimalFormat fourD = new DecimalFormat("0.0000");
      DecimalFormat fiveD = new DecimalFormat("0.00000");

      System.out.println("Statistics");
      System.out.println("________________________________________________");
      System.out.println("Linear search average = " + fiveD.format(linear_avg / 1_000_000_000.0) + " s");
      System.out.println("Linear search stddev  = " + fourD.format(linear_std / 1_000_000.0) + " ms");
      System.out.println("Binary search average = " + fiveD.format(binary_avg / 1_000_000_000.0) + " s");
      System.out.println("Binary search stddev  = " + fourD.format(binary_std / 1_000_000.0) + " ms");
      System.out.println("n = " + N + ", repetitions = " + REPS);
      System.out.println("________________________________________________");
   }

 
   public static int linearsearch(Node[] a, int key) {
      for (int i = 0; i < a.length; i++) {
         if (a[i].key == key) return i;
      }
      return -1;
   }


   public static int binarysearch(Node[] a, int key) {
      int left = 0, right = a.length - 1;
      while (left <= right) {
         int mid = left + (right - left) / 2;
         if (a[mid].key == key) return mid;
         if (a[mid].key < key) left = mid + 1;
         else right = mid - 1;
      }
      return -1;
   }

   private static double average(long[] arr) {
      double s = 0.0;
      for (long v : arr) s += v;
      return s / arr.length;
   }

   private static double stddev(long[] arr, double mean) {
      double s = 0.0;
      for (long v : arr) {
         double d = v - mean;
         s += d * d;
      }
      return Math.sqrt(s / (arr.length - 1));
   }
}