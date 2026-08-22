package com.DeatHertZ.urlshortener.service;

import java.util.Random;

public class GenerateShortCode {
    private StringBuilder shortCode;
    private int origin, bound;
    private int GenerateNumber;
    // Short code Can have a-z, A-Z, 0-9 in random
    private final int[] arr1 = {65,91};
    private final int[] arr2 = {97,123};
    private final int[] arr3 = {48, 58};

    public StringBuilder getShortCode() {
        return shortCode;
    }

    private void setShortCode(StringBuilder shortCode) {
        this.shortCode = shortCode;
    }

    private int getGenerateNumber(){
        return GenerateNumber;
    }

    private void setGenerateNumber(int GenerateNumber){
        this.GenerateNumber = GenerateNumber;
    }

    private int getOrigin() {
        return origin;
    }

    private void setOrigin(int origin) {
        this.origin = origin;
    }

    private int getBound() {
        return bound;
    }

    private void setBound(int bound) {
        this.bound = bound;
    }

    public void generate()
    {


        Random rand = new Random();
        setGenerateNumber(rand.nextInt(1,4)); // Generating a random number between 1 - 4 exclusive 4 to choose from the array range

        int value = getGenerateNumber();
        int og, bd;

        if(value == 1){ // Array 1 ASCII range 65,90
            // Setting the value of origin and bound for the next in range generation
            setOrigin(arr1[0]);
            og = getOrigin();

            setBound(arr1[1]);
            bd = getBound();

        } else if(value == 2) { // Array 1 ASCII range 65,90
            // Setting the value of origin and bound for the next in range generation
            setOrigin(arr2[0]);
            og = getOrigin();

            setBound(arr2[1]);
            bd = getBound();

        }  else { // Array 1 ASCII range 65,90
            // Setting the value of origin and bound for the next in range generation
            setOrigin(arr3[0]);
            og = getOrigin();

            setBound(arr3[1]);
            bd = getBound();

        }

        StringBuilder shortCode1 = new StringBuilder();
        int len = 0;
        while(len < 9){ // Length should not be greater than 9 characters

            setGenerateNumber(rand.nextInt(og,bd)); // Generate a random number from the range for the array set previously
            shortCode1.append((char)getGenerateNumber()); // append the character value to the stringBuilder

            len = shortCode1.length();
        }

        // Once done we set the final shortcode to be sent to the USER
        setShortCode(shortCode1);
    }
}