package lab_quation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class fractional_knapsack {
        static void print(String[] arr) {
            for (String s : arr) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

        static void print(int arr[]) {
            System.out.println(Arrays.toString(arr));
        }

        static void print(float arr[]) {
            System.out.println(Arrays.toString(arr));
        }

        static void combine(String x[], int w[], int v[], float p[], int low, int mid, int high) {
            int i = low;
            int j = mid + 1;
            int n = high - low + 1;

            String[] xrr = new String[n];
            int[] wrr = new int[n];
            int[] vrr = new int[n];
            float[] prr = new float[n];

            int idx = 0;

            while (i <= mid && j <= high) {
                if (p[i] >= p[j]) {
                    xrr[idx] = x[i];
                    wrr[idx] = w[i];
                    vrr[idx] = v[i];
                    prr[idx] = p[i];
                    i++;
                } else {
                    xrr[idx] = x[j];
                    wrr[idx] = w[j];
                    vrr[idx] = v[j];
                    prr[idx] = p[j];
                    j++;
                }
                idx++;
            }

            while (i <= mid) {
                xrr[idx] = x[i];
                wrr[idx] = w[i];
                vrr[idx] = v[i];
                prr[idx] = p[i];
                i++;
                idx++;
            }

            while (j <= high) {
                xrr[idx] = x[j];
                wrr[idx] = w[j];
                vrr[idx] = v[j];
                prr[idx] = p[j];
                j++;
                idx++;
            }

            for (int k = 0; k < n; k++) {
                x[low + k] = xrr[k];
                w[low + k] = wrr[k];
                v[low + k] = vrr[k];
                p[low + k] = prr[k];
            }
        }

        static void merge_sort(String x[], int w[], int v[], float p[], int low, int high) {
            if (low < high) {
                int mid = (low + high) / 2;
                merge_sort(x, w, v, p, low, mid);
                merge_sort(x, w, v, p, mid + 1, high);
                combine(x, w, v, p, low, mid, high);
            }
        }

        // Fractional Knapsack.................
        static void knapsackFractional(String[] x, int[] weight, int[] values,int w) {

            int n = x.length;
            float[] profitRatio = new float[n];
            for (int i = 0; i < n; i++) {
                if (weight[i] == 0) {
                    System.out.println("Error: Weight cannot be zero for item " + x[i]);
                    return;
                }
                profitRatio[i] = (float) values[i] / weight[i];
            }
            System.out.println("Profit Ratio: ");
            print(profitRatio);
            //sort using profitRation in descending order..............
            merge_sort(x, weight, values, profitRatio, 0, n - 1);

            int idx = 0;
            float Totalprofit = 0;
            ArrayList<String> result= new ArrayList<>();
            while (w > 0 && idx < n) {
                if (weight[idx] <= w) {
                    result.add(x[idx] + " (Weight: " + weight[idx] + ", Value: " + values[idx] + ")");
                    w -= weight[idx];
                    Totalprofit += values[idx];
                } else {
                    float fraction = (float) w / weight[idx];
                    result.add(x[idx] + " (Weight: " + w + "/" + weight[idx] + ", Value: " + (fraction * values[idx]) + ")");
                    Totalprofit += fraction * values[idx];
                    w = 0;
                }
                idx++;
            }
            System.out.println("Fractional Knapsack Results:");
            System.out.println("Max Profit: " + Totalprofit);
            //final result................
            System.out.println("Selected items:");
            for (String item : result) {
                System.out.println(item);
            }
            System.out.println("Remaining capacity: " + w);
        }

        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter number of Items:");
            int n=sc.nextInt();

            String[] x = new String[n];
            int[] weight = new int[n];
            int[] values = new int[n];

            //input from user.....
            System.out.println("Enter capacity of knapsack w:");
            int w=sc.nextInt();
            System.out.println("Enter Items weight:");
            for(int i=0;i<n;i++){
                x[i]="i"+i;
                weight[i]=sc.nextInt();
            }
            System.out.println("Enter Items values:");
            for(int i=0;i<n;i++){
                values[i]=sc.nextInt();
            }

            //print items,weight and values...
            System.out.println("Items:");
            print(x);
            System.out.println("Items weight:");
            print(weight);
            System.out.println("Items cost:");
            print(values);

            long startTime=System.nanoTime();
            knapsackFractional(x, weight, values,w);
            long endTime=System.nanoTime();
            System.out.println("Time Complexity of Fractional knapsack using Greedy is: "+(endTime-startTime)/1000000.0+" ms");
        }
    }
