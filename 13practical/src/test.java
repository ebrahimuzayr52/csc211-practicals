// Code is stored as 13template.java
import java.lang.Math.*;   
import java.io.*;   
import java.text.*;
import java.util.Random;

public class test{
   public static final int N = 32655;   //total dataset
    public static final int reps = 30;  //repetitions done
public static void main(String args[]){
   int[] target = new int[N];
        for (int j = 0; j < N; j++) {
            target[j] = j + 1;   
        }

        Random rand = new Random();
        double linear = 0;
        double linear_total_squared = 0;
        double b_total = 0;
        double b_total_squared = 0;

        for (int i=0;i<reps;i++){
            int key = 1 + rand.nextInt(N);
            long start = System.nanoTime();
            linearsearch(target, key);
            long finish = System.nanoTime();
            long linearTime = finish - start;

            linear += linearTime;
            linear_total_squared += (double) linearTime * linearTime;

            
            start = System.nanoTime();
            binarysearch(target, key);
            finish = System.nanoTime();
            long binaryTime = finish - start;

            b_total += binaryTime;
            b_total_squared += (double) binaryTime * binaryTime;
        }

        
         double linearAverage = linear / reps;
        double linearStdDev =
                Math.sqrt((linear_total_squared - reps * linearAverage * linearAverage)
                        / (reps - 1));

        double binaryAverage = b_total / reps;
        double binaryStdDev =
                Math.sqrt((b_total_squared - reps * binaryAverage * binaryAverage)
                        / (reps - 1));

DecimalFormat twoD = new DecimalFormat("0.00");
DecimalFormat fourD = new DecimalFormat("0.0000");
DecimalFormat fiveD = new DecimalFormat("0.00000");

long start, finish;
double runTime = 0, runTime2 = 0, time;
double totalTime = 0.0;
int n = N;
int repetition, repetitions = 30;

   runTime = 0;
   for(repetition = 0; repetition < repetitions; repetition++) {
      start = System.currentTimeMillis();
		
     
      linearsearch(target, 1 + rand.nextInt(N));
      binarysearch(target, 1 + rand.nextInt(N));
      
		
      finish = System.currentTimeMillis();
			
      time = (double)(finish - start);
      runTime += time;
      runTime2 += (time*time); }

   double aveRuntime = runTime/repetitions;
   double stdDeviation = 
      Math.sqrt(runTime2 - repetitions*aveRuntime*aveRuntime)/(repetitions-1);

   System.out.printf("\n\n\fStatistics\n");
   System.out.println("________________________________________________");
   System.out.println("Total time   =           " + runTime/1000 + "s.");
   System.out.println("Total time\u00b2  =           " + runTime2);
   System.out.println("Average time =           " + fiveD.format(aveRuntime/1000)
                     + "s. " + '\u00B1' + " " + fourD.format(stdDeviation) + "ms.");
   System.out.println("Standard deviation =     " + fourD.format(stdDeviation));
   System.out.println("n            =           " + n);
   System.out.println("Average time / run =     " + fiveD.format(aveRuntime/n*1000) 
                     + '\u00B5' + "s. "); 

   System.out.println("Repetitions  =             " + repetitions);
   System.out.println("________________________________________________");
   System.out.println();
   System.out.println(); }	

public static int linearsearch(int[]target, int key){
   for (int i=0; i<target.length; i++){
      if (target[i] == key){
         return i;
      }
   }
   return -1;
}

public static int binarysearch(int[]target, int key){
   int left = 0;
   int right = target.length - 1;

   while (left <= right) {
      int mid = left + (right - left)/2;

      if (target[mid] == key) {
         return mid;
      } else if (target[mid] < key) {
         left = mid + 1;
      } else {
         right = mid - 1;
      }
   }
   return -1;

}}