
package EXO2;

import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;

        do {
            System.out.print("la valeur de n est: ");
            n = scanner.nextInt();
        } while (n < 1);

        int u1 = 1, u2 = 1;

        if (n == 1 || n == 2) {
            System.out.printf("la valeur de U%d = 1\n", n);
        } else {
            int un = 0;
            int i = 3;
            while (i <= n) {
                un = u1 + u2;
                u2 = u1;
                u1 = un;
                i++;
            }

            System.out.printf("la valeur de U%d = %d\n", n, un);
        }

        scanner.close();
    }
}
