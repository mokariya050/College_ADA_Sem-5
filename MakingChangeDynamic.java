package lab_quation;
import java.util.ArrayList;
import java.util.Scanner;

public class making_change_dynamic {
    static int min(int a,int b){
        if(a<b){
            return a;
        }
        else{
            return b;
        }
    }
    static void making_change(int coin[],int change){
        int a= coin.length;
        int infinite=Integer.MAX_VALUE/2;

        int arr[][]=new int[a+1][change+1];

        for(int i=1;i<=change;i++){
            arr[0][i]=infinite;
        }
        for(int i=1;i<=a;i++){
            for(int j=1;j<=change;j++){

                if(coin[i-1]>j){
                    arr[i][j]=arr[i-1][j];
                }
                else{
                    arr[i][j]=min(arr[i-1][j],1+arr[i][j-coin[i-1]]);
                }
            }
        }
        System.out.println("DP table is:");
        for (int i = 0; i <= a; i++) {
            for (int j = 0; j <= change; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        if(arr[a][change]==infinite){
            System.out.println("change is not possible");
            return;
        }
        else{
            System.out.println("number of coin is: "+arr[a][change]);
        }
        ArrayList<Integer> result=new ArrayList<>();
        int j= coin.length;
        int k=change;
       while(j>0 && k>0){
           if(j>1 && arr[j][k]==arr[j-1][k]) {
               j=j-1;
           }
           else{
               result.add(coin[j-1]);
               k-=coin[j-1];

           }
        }
        System.out.println("your coin is: "+result);
        System.out.println("Remaining change (if any): " + k);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount you want to change: ");
        int change = sc.nextInt();

        // User input for coin denominations
        System.out.print("Enter number of coin denominations: ");
        int n = sc.nextInt();
        int coin[] = new int[n];
        System.out.println("Enter the coin denominations:");
        for (int i = 0; i < n; i++) {
            coin[i] = sc.nextInt();
        }

        long startTime = System.nanoTime();
        making_change(coin, change);
        long endTime = System.nanoTime();

        System.out.printf("Time Complexity for making change using Dynamic Programming: "+
                (endTime - startTime) / 1000000.0+" ms");

        sc.close();

    }
}
