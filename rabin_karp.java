package lab_quation;
import java.util.Scanner;

public class rabin_karp {

    static void rabinKarp(String text, String pattern) {
        text = text.trim();
        pattern = pattern.trim();

        int n = text.length();
        int m = pattern.length();
        int d = 256; // number of characters in input alphabet
        int q = 101; // prime modulus

        int pHash = 0;
        int tHash = 0;
        int h = 1;

        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;

        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pattern.charAt(i)) % q;
            tHash = (d * tHash + text.charAt(i)) % q;
        }

        System.out.println("\n--- Pattern Occurrences ---");
        boolean found = false;

        for (int i = 0; i <= n - m; i++) {
            if (pHash == tHash) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < m; j++)
                    sb.append(text.charAt(i + j));

                if (sb.toString().equals(pattern)) {
                    System.out.println("Pattern found at index: " + i);
                    found = true;
                }
            }

            if (i < n - m) {
                tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % q;
                if (tHash < 0)
                    tHash += q;
            }
        }

        if (!found) System.out.println("Pattern not found in the text.");
        System.out.println("\nTheoretical Time Complexity: O(n + m)");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your text: ");
        String text = sc.nextLine().trim();

        System.out.print("Enter the pattern you want to find: ");
        String pattern = sc.nextLine().trim();

        long startTime = System.nanoTime();
        rabinKarp(text, pattern);
        long endTime = System.nanoTime();

        System.out.println("Time Complexity of rabin-karp algorithms is: "+(endTime - startTime) / 1000000.0+" ms");
        sc.close();
    }
}
