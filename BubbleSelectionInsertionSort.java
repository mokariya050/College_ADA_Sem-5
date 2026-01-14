package lab_quation;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BubbleSelectionInsertionSort {
    //insertion sort....................................................................
    static void insertionSort(int arr[],int n){
        for(int i=1;i<n;i++){
            int key=arr[i];
            int j=i-1;
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;

            }
            arr[j+1]=key;
        }

    }

    //selection sort.......................................................................
    static void selectionSort(int arr[],int n){
        for(int i=0;i<n-1;i++){
            int minindex=i;
            for(int j=i+1;j<n;j++){
                if(arr[minindex]>arr[j]){
                    minindex=j;
                }
            }
            if(minindex!=i){
                int temp = arr[i];
                arr[i] = arr[minindex];
                arr[minindex] = temp;
            }
        }
    }

        //optimal bubble sort.......................................................................
        static void optimal_bubbleSort(int arr[],int n){
            for(int i=0;i<n-1;i++){
                boolean swap=false;
                for(int j=0;j<n-1-i;j++){
                    if(arr[j]>arr[j+1]){

                        int temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                        swap=true;
                    }
                }
                if(swap==false){
                    break;
                }
            }
    }
        //print array......................................................................
        static void print_array(int arr[]){
            System.out.println(Arrays.toString(arr));
        }
        //bubble sort............................................................................
        static void bubbleSort(int arr[],int n){
            for(int i=0;i<n-1;i++){
                for(int j=0;j<n-1-i;j++){
                    if(arr[j]>arr[j+1]){
                        int temp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=temp;
                    }
                }
            }

        }
        //..........................main method..........................................................
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            Random r=new Random();
            System.out.println("enter size of array:");
            int n=sc.nextInt();

            int arr[] = new int[n];
            //input of array using random class----------------------------------------
            for(int i=0;i<arr.length;i++){
                arr[i]=r.nextInt(100);
            }
            System.out.println("your unsorted array is:");
            print_array(arr);

            while(true){
                System.out.println("enter your choice:");
                System.out.println("1.bubble sort");
                System.out.println("2.selection sort");
                System.out.println("3.insertion sort");
                System.out.println("4.optimal bubble sort");
                System.out.println("5.Exit");

                int c=sc.nextInt();
                //clone array---------------------------------------
                int arrCopy[]=arr.clone();

                switch (c){
                    case 1:
                        long timeStart=System.nanoTime();
                        bubbleSort(arrCopy, arrCopy.length);
                        long timeEnd=System.nanoTime();
                        System.out.println("Sorted Array is:");
                        print_array(arrCopy);
                        System.out.println("algorithm time for bubble sort is: "+(timeEnd-timeStart)/1000000.0+" milliseconds");
                        System.out.println("------------------------------------------------------------------------------------------------");
                        break;

                    case 2:    timeStart=System.nanoTime();
                        selectionSort(arrCopy, arrCopy.length);
                         timeEnd=System.nanoTime();
                        System.out.println("Sorted Array is:");
                        print_array(arrCopy);

                        System.out.println("algorithm time for selection sort is: "+(timeEnd-timeStart)/1000000.0+" milliseconds");
                        System.out.println("------------------------------------------------------------------------------------------------");
                        break;

                    case 3:  timeStart=System.nanoTime();
                        insertionSort(arrCopy, arrCopy.length);
                        timeEnd=System.nanoTime();
                        System.out.println("Sorted Array is:");
                        print_array(arrCopy);
                        System.out.println("algorithm time for insertion sort is: "+(timeEnd-timeStart)/1000000.0+" milliseconds");
                        System.out.println("------------------------------------------------------------------------------------------------");
                        break;

                    case 4: timeStart=System.nanoTime();
                        optimal_bubbleSort(arrCopy,arrCopy.length);
                        timeEnd=System.nanoTime();
                        System.out.println("Sorted Array is:");
                        print_array(arrCopy);

                        System.out.println("algorithm time for optimal bubble sort is: "+(timeEnd -timeStart)/1000000.0+" milliseconds");
                        System.out.println("------------------------------------------------------------------------------------------------");
                        break;
                    case 5:System.out.println("Exiting program. Goodbye!");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("invalid choice.");
                        break;
                }
            }
    }
    }


















