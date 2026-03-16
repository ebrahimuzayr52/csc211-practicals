import java.io.*;
import java.util.*;


public class heapsort {
    static void shift_Down(String[] arr, int i, int size) {
        while(true){
            int left = 2*i+2;
            int right= 2*i+1;
            int parent_small= i+1;

            if(left<size && arr[left].compareTo(arr[parent_small])<0){
                parent_small=left;
            }
            if(right<size&& arr[right].compareTo(arr[parent_small])<0){
                parent_small=right;
            }
            if(parent_small ==i){
                break;
            }

            String temp = arr[i];
            arr[i]=arr[parent_small];
            arr[parent_small] = temp;
            i = parent_small;
        }
    }
    static void shift_up(String[] arr, int i, int size){
        while(i>0){
            int parent= (i-1)/2;
            if(arr[i].compareTo(arr[parent])<0){
                String temp=arr[i];
                arr[i]=arr[parent];
                arr[parent]=temp;
                i=parent;

            }else
                {
                break;
            }

        }
    }
    static void build_up(String []arr, int size){

        for(int i= size/2-1; i>=0 ; i--){
            shift_Down(arr, i, size);
        }
    }
    static void Top_Down(String[] arr, int size) {
        for(int i=1; i< size;i++ ){
            shift_up(arr,i);
        }
    }
    static String[] heapSort(String[] arr, int size) {
        String[] heap = Arrays.copyOf(arr, size);
        int w = size;

        while(w>1){
             String tmp = heap[0];
            heap[0] = heap[w - 1];
            heap[w - 1] = tmp;
            w--;
            shift_Down(heap, 0, w);
        }
        String[] sorted= new String[size];
        for(int i=0;i<size;i++){
            sorted[i] = heap[size - 1 - i];
        }
        return sorted;
    }
     static String[] Reader(String path) throws IOException {
        Set<String> words = new LinkedHashSet<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            
            String cleaned = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase();
            for (String z : cleaned.split("\\s+")) {
                if (!z.isEmpty()) words.add(z);
            }
        }
        br.close();
        return words.toArray(new String[0]);
    }
    static String ms(long nanos) {
        return String.format("%.3f ms", nanos / 1_000_000.0);
    
    }
    public static void main(String[] args){
        String filePath = (args.length > 0) ? args[0] : "ullysses.txt";
        System.out.println("LOADING WORDS FROM "+ filePath);
        String[] words = Reader(filePath);
        System.out.printf("Words loaded: %,d%n%n", words.length);

        final int REPS = 5;
        long totalBuildBU = 0, totalSortBU = 0;
        String[] sortedBU = null;

        for (int r = 0; r < REPS; r++) {
            String[] arr = Arrays.copyOf(words, words.length);

            long t0 = System.nanoTime();
            build_up(arr, arr.length);
            long t1 = System.nanoTime();
            sortedBU = heapSort(arr, arr.length);
            long t2 = System.nanoTime();

            totalBuildBU += (t1 - t0);
            totalSortBU  += (t2 - t1);
        }
        long avgBuildBU = totalBuildBU / REPS;
        long avgSortBU  = totalSortBU  / REPS;

        long totalBuildTD = 0, totalSortTD = 0;
        String[] sortedTD = null;

        for (int r = 0; r < REPS; r++) {
            String[] arr = Arrays.copyOf(words, words.length);

            long t0 = System.nanoTime();
            Top_Down(arr, arr.length);
            long t1 = System.nanoTime();
            sortedTD = heapSort(arr, arr.length);
            long t2 = System.nanoTime();

            totalBuildTD += (t1 - t0);
            totalSortTD  += (t2 - t1);
        }
        long avgBuildTD = totalBuildTD / REPS;
        long avgSortTD  = totalSortTD  / REPS;
        
    }






        





        







    }
}
}



