import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n: ");
        String line = scanner.nextLine();
        int n = Integer.parseInt(line);
        System.out.println(findMaxBinary(n));

    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i ++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }
    public static int findMaxBinary(int n) {
        double log2 = Math.floor(Math.log(n) / Math.log(2));
        return (int)Math.pow(2, log2);
    }
}