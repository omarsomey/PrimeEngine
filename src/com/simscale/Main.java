package com.simscale;

import java.util.*;

public class Main {

    public static final Integer max_size = 1000000;
    public static ArrayList<Integer> primes = SieveOfEratosthenes(); //Build the ArrayList of primes with the Sieve method just Once.


    public static void main(String[] args) {
        System.out.println(max_size);

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, This is a java prime generator ! \n Please choose one of these strategies to generate the primes :");
        System.out.println("Enter the number of the strategy \n 1: Brute Forece \n 2: Sieve of Erastothenes \n 3: Sieve of Sundaram");
        System.out.println("Please enter the number of the method : ");
        int strat = sc.nextInt();
        System.out.println("Please enter the first limit of the range : ");
        int left = sc.nextInt();
        System.out.println("Enter the second limit of the range : ");
        int right = sc.nextInt();
        ArrayList<Integer> res = new ArrayList<>();

        switch (strat){
            case 1: res = bruteForce( left, right);
            case 2: res = getBetween(left, right) ;
                    break;
            default: res = sieveOfSundaram(left, right);
        }


        System.out.println("The primes between "+left+" and "+right+" are :");
        System.out.println(res);


    }

    //BruteForce method returns an ArrayList containing the primes between left and right using the simplest method of primes generation
    public static ArrayList<Integer> bruteForce(int left, int right){
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = left; i <= right; i++)  {
            if ( i <= 1)  continue;
            boolean isPrime = true;
            for (int j = 2; j <= i / 2; ++j) {
                if (i % j == 0) {
                    isPrime= false;
                    break;
                }
            }
            if (isPrime) res.add(i);
        }
        return res;
    }

    // SieveOfEratosthenes method returns an ArrayList of all the primes between 1 and (2^31-1) using the sieve of eratosthenes method
    public static ArrayList<Integer> SieveOfEratosthenes()
    {
        ArrayList<Integer> res = new ArrayList<>();
        boolean prime[] = new boolean[max_size+7]; // prime is an array to mark all primes

        //Mark all integers to be primes
        for(int i=0;i<max_size;i++) {
            prime[i] = true;
        }

        // For each integer cross all of his multiples
        for(int p = 2; p*p <=max_size; p++) {
            if(prime[p] == true) {
                for(int i = p*2; i <= max_size; i += p)  prime[i] = false;
            }
        }

        // Add the uncrossed primes to res
        for(int i = 2; i <= max_size; i++) {
            if(prime[i] == true) res.add(i);
        }

        return res;

    }

    //getBetween is a method that returns an ArrayList from another ArrayLst (primes) between left and right parameters
    public static  ArrayList<Integer> getBetween(int left, int right){
        ArrayList<Integer> res = new ArrayList<>();
        int pos1 = Collections.binarySearch(primes,left);
        int pos2 = Collections.binarySearch(primes,right);
        if(pos1<0) pos1=Math.abs(pos1+1);
        if(pos2<0) pos2=Math.abs(pos2+2);
        for(int i=pos1;i<=pos2;i++){
            res.add(primes.get(i));
        }
        return  res;
    }

    private static ArrayList<Integer> sieveOfSundaram(int l, int r) {

        int rightboundary = (r - 2) / 2;
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] marked = new boolean[rightboundary + 1];
        Arrays.fill(marked, false);

        for (int i = 1; i <= rightboundary; i++)
            for (int j = i; (i + j + 2 * i * j) <= rightboundary; j++)
                marked[i + j + 2 * i * j] = true;

        if (r >= 2 && l <= 2) primes.add(2);

        for (int i = 1; i <= rightboundary; i++)
            if (!marked[i] && 2 * i + 1 >= l && 2 * i + 1 <= r) primes.add(2 * i + 1);

        return primes;
    }

}
