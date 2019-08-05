package com.simscale;

import java.util.*;

public class PrimeChecker {

    public static final Integer max_size = 1000000;
    public static ArrayList<Integer> primes = SieveOfEratosthenes(); //Build the ArrayList of primes with the Sieve method just Once.

    public PrimeChecker() {
    }

    public static void main(String[] args) {

        String quit="n";
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, This is a java prime generator ! \n Please choose one of these strategies to generate the primes :");

        while(quit.equals("n")) {

            System.out.println("Enter the number of the strategy \n 1: Brute Forece \n 2: Sieve of Erastothenes \n 3: Sieve of Sundaram");
            System.out.println("Please enter the number of the method : ");
            int strat = sc.nextInt();
            System.out.println("Please enter the first limit of the range : ");
            int left = sc.nextInt();
            System.out.println("Enter the second limit of the range : ");
            int right = sc.nextInt();
            ArrayList<Integer> res = new ArrayList<>();

            // switch between the strategies
            switch (strat){
                case 1: res = bruteForce( left, right);
                case 2: res = getBetween(left, right) ;
                    break;
                default: res = sieveOfSundaram(left, right);
            }


            System.out.println("The primes between "+left+" and "+right+" are :");
            System.out.println(res);
            System.out.println("do you want to quit? y or n \n");
            quit = sc.next();

        }


    }

    /**
     * Method to compute the primes by the brute force method.
     *
     * @param left  is an Integer
     * @param right is an Integer
     * @return an ArrayList with the primes between the range
     */
    public static ArrayList<Integer> bruteForce(int left, int right) {
        if( right < left) {
            return new ArrayList<>();
        }
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = left; i <= right; i++) {
            if (i <= 1) continue;

            // isPrime indicates if the number is a prime or not.
            //we set i to be prime until proven wrong.
            boolean isPrime = true;
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            //add the number to res  if he's prime.
            if (isPrime) res.add(i);
        }
        return res;
    }

    /**
     * Method to compute the primes by the Sieve of Eratosthenes method.
     * We call this method once at the start of the application and at
     * every request from the client we just a use a binary search to fetch
     * the primes.
     *
     * @return an ArrayList with the primes between 1 and max_size
     */
    public static ArrayList<Integer> SieveOfEratosthenes() {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] prime = new boolean[max_size + 7];

        // Mark all numbers to be Primes
        for (int i = 0; i < max_size; i++) {
            prime[i] = true;
        }

        //Cross all multiples from prime
        for (int p = 2; p * p <= max_size; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= max_size; i += p) prime[i] = false;
            }
        }

        for (int i = 2; i <= max_size; i++) {
            if (prime[i]) res.add(i);
        }
        return res;
    }

    /**
     * Method to compute the primes by the Sieve of Sundaram method.
     * the maximum right boundary is 65000 for this strategy
     *
     * @param l is an int
     * @param r is an int
     *
     * @return an ArrayList with the primes between l and r
     */
    public static ArrayList<Integer> sieveOfSundaram(int l, int r) {

        // Since we want primes smaller than r, we reduce n to half
        int rightboundary = (r - 2) / 2;

        ArrayList<Integer> primes = new ArrayList<>();

        // This array is used to separate numbers of the form i+j+2ij
        // from others where  1 <= i <= j
        boolean[] marked = new boolean[rightboundary + 1];

        // Initalize all elements as not marked
        Arrays.fill(marked, false);

        // Main logic of Sundaram.  Mark all numbers of the
        // form i + j + 2ij as true where 1 <= i <= j
        for (int i = 1; i <= rightboundary; i++)
            for (int j = i; (i + j + 2 * i * j) <= rightboundary; j++)
                marked[i + j + 2 * i * j] = true;

        //2 is a prime number
        if (r >= 2 && l <= 2) primes.add(2);

        // Print other primes. Remaining primes are of the form
        // 2*i + 1 such that marked[i] is false
        // and such that 2*i + 1 between l and r
        for (int i = 1; i <= rightboundary; i++)
            if (!marked[i] && 2 * i + 1 >= l && 2 * i + 1 <= r) primes.add(2 * i + 1);

        return primes;
    }

    /**
     * Method that performs a Binary search on a sorted ArrayList
     * to get the sub ArrayList between "left" and "right"
     *
     * @param left  is an Integer
     * @param right in an INteger
     * @return a sub ArrayList with the numbers the between left and right
     */
    public static ArrayList<Integer> getBetween(int left, int right) {
        ArrayList<Integer> res = new ArrayList<>();
        int pos1 = Collections.binarySearch(primes, left);
        int pos2 = Collections.binarySearch(primes, right);
        if (pos1 < 0) pos1 = Math.abs(pos1 + 1);
        if (pos2 < 0) pos2 = Math.abs(pos2 + 2);
        for (int i = pos1; i <= pos2; i++) {
            res.add(primes.get(i));
        }
        return res;
    }

}
