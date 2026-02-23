// Code is stored as 13template.java
import java.lang.Math.*;   
import java.io.*;   
import java.text.*;
import java.util.Random;

public class timeMethods{
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
            linearSearch(target, key);
            long finish = System.nanoTime();
            long linearTime = finish - start;

            linear += linearTime;
            linear_total_squared += (double) linearTime * linearTime;

            
            start = System.nanoTime();
            binarySearch(target, key);
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
		
      // call the procedures to time here:
      linearsearch (...);
      binarysearch (...);
      // Figure out how to alter this guideline here,
		
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
   System.out.println(); }	} 

static void oneofyourMethods(int n, 
                       yourMethodParameter1,
                       yourMethodParameter2, . . . ) {
// The declarations and body of your method / s  
// The final statement of this code.
} 