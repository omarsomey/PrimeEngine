package com.simscale;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeCheckerTest {

    @Test
    public void bruteForceLeft5Right7Test() {
        PrimeChecker primeChecker = new PrimeChecker(); // PrimeChecker is tested

        ArrayList<Integer> result = new ArrayList<>();
        result.add(5);
        result.add(7);
        // assert statements
        assertEquals(primeChecker.bruteForce(5, 7), result);
    }

    @Test
    public void bruteForceLeft5Right4Test() {
        PrimeChecker primeChecker = new PrimeChecker(); // PrimeChecker is tested
        // assert statements
        assertEquals(primeChecker.bruteForce(5, 4), new ArrayList<>());
    }

    @Test
    public void bruteForceLeft5Right5Test() {
        PrimeChecker primeChecker = new PrimeChecker(); // PrimeChecker is tested
        ArrayList<Integer> result = new ArrayList<>();
        result.add(5);
        // assert statements
        assertEquals(primeChecker.bruteForce(5, 5), result);
    }
}