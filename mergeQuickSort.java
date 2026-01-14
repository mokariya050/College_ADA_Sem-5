package lab_quation;
import java.util.Arrays;
import java.util.Random;
import java .util.Scanner;

public class mergeQuickSort {
    //quick sort..............
    static void quick_sort(int arr[],int low,int high){
        if(low<high){
            int pivot=partition(arr,low,high);
            quick_sort(arr,low,pivot-1);
            quick_sort(arr,pivot+1,high);
        }
    }
    static int partition(int arr[], int low, int high) {
        int pivot = arr[low];

        int left = low + 1;
        int right = high;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) left++;
            while (left <= right  && arr[right] > pivot) right--;

            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        int temp = arr[low];
        arr[low] = arr[right];
        arr[right] = temp;

        return right;
    }

    //swap function.....................
    static void swap(int i,int j){
        int temp=i;
        i=j;
        j=temp;
    }
    //merge sort...............................
    static void merge_sort(int arr[],int low,int high){
        if(low<high){
            int mid=(high+low)/2;
            merge_sort(arr,low,mid);
            merge_sort(arr,mid+1,high);
            combine(arr,low,mid,high);
        }
    }
    //combine operation in merge sort.....................
    static void combine(int arr[], int low, int mid, int high) {
        int[] brr = new int[high - low + 1];
        int i = low, j = mid + 1, k = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                brr[k++] = arr[i++];
            } else {
                brr[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            brr[k++] = arr[i++];
        }

        while (j <= high) {
            brr[k++] = arr[j++];
        }

        for (int x = 0; x < brr.length; x++) {
            arr[low + x] = brr[x];
        }
    }

    //print array
    static void print_array(int arr[]){
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random r=new Random();
        System.out.println("enter size of array:");
        int n=sc.nextInt();
        int arr[]=new int[n];

        //input using Random class
        for(int i=0;i<n;i++){
            arr[i]=r.nextInt(100);
        }
        while (true) {
            //array clone..
            int[] arrCopy = arr.clone();
            System.out.println("\nChoose an option:");
            System.out.println("1. Merge Sort");
            System.out.println("2. Quick Sort");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    long startMerge = System.nanoTime();
                    merge_sort(arrCopy, 0, arr.length - 1);
                    long endMerge = System.nanoTime();
                    System.out.println("Sorted array using Merge Sort:");
                    print_array(arrCopy);
                    System.out.println("Time: " + (endMerge - startMerge) / 1_000_000.0 + " ms");
                    break;

                case 2:
                    long startQuick = System.nanoTime();
                    quick_sort(arrCopy, 0, arr.length - 1);
                    long endQuick = System.nanoTime();
                    System.out.println("Sorted array using Quick Sort:");
                    print_array(arrCopy);
                    System.out.println("Time: " + (endQuick - startQuick) / 1000000.0 + " ms");
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
 }






    }
}
