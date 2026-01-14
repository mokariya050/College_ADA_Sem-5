package lab_quation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class knapsack_dynamic {

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

    static int max(int a,int b){
        if(a>b){
            return a;
        }
        else{
            return b;
        }
    }

    static void knapsack_0_1(String[] item,int weight[],int values[],int w){
        int n=item.length;
        int arr[][]=new int [n+1][w+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=w;j++){
                if(weight[i-1]>j){
                    arr[i][j]=arr[i-1][j];
                }
                else{
                    arr[i][j]=max(arr[i-1][j],values[i-1]+arr[i-1][j-weight[i-1]]);
                }
            }
        }

        if(arr[n][w]==0){
            System.out.println("No items can be included in the knapsack.");
            return;
        }
        else{
            System.out.println("your total profit is: "+arr[n][w]);
        }
        ArrayList<String> result=new ArrayList<>();
        int i=n ;
        int j=w;
        while(i>0 && j>0){
            if(arr[i][j]==arr[i-1][j]){
                i--;
            }
            else{
                result.add(item[i-1]);
                j=j-weight[i-1];
                i--;

            }
        }
        System.out.println("your selected items are:"+result);
        System.out.println("Remaining capacity in knapsack: " + j);

    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //input from user............
        System.out.println("Enter number of Items:");
        int n=sc.nextInt();

        String[] x = new String[n];
        int[] weight = new int[n];
        int[] values = new int[n];

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
        knapsack_0_1(x,weight,values,w);
        long endTime=System.nanoTime();
        System.out.println("Time Complexity of 0_1-knapsack using dynamic programing is: "+(endTime-startTime)/1000000.0+" ms");
        System.out.println("Theoretical Time Complexity: O(n * w)");

    }
}
