import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = readN(scanner);
        System.out.println(findMaxZeroCount(n));
    }

    public static int readN(Scanner scanner) {
        while (true) {
            System.out.println("Enter n: ");
            String line = scanner.nextLine();
            try {
                int n = Integer.parseInt(line.trim());
                if (n < 2) {
                    System.out.println("Error: n must be an integer greater than or equal to 2. Try again.");
                    continue;
                }
                return n;
            } catch (NumberFormatException e) {
                System.out.println("Error: \"" + line + "\" is not a valid integer. Try again.");
            }
        }
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i ++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Integer> getAllPrimes(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 2; i < n; i ++) {
            if (isPrime(i)) {
                result.add(i);
            }
        }
        return result;
    }
    public static int calculateZeroCount(int n) {
        int i = n;
        int result = 0;

        while (i != 0) {
            int x = i & 0b1;
            if (x == 0) result++;
            i = i >> 1;
        }

        return result;
    }
    public static int findMaxZeroCount(int n) {
        ArrayList<Integer> primes = getAllPrimes(n);
        int result = primes.get(0);

        for (int prime : primes) {
            if (calculateZeroCount(result) < calculateZeroCount(prime)) {
                result = prime;
            }
        }
        return result;
    }
}