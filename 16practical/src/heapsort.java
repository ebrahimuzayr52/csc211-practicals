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
    static void buildup(String []arr, int size){

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


    }


        





        







    }
}
}



