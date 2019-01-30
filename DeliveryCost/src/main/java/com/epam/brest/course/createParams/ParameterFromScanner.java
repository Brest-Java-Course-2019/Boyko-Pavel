package com.epam.brest.course.createParams;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ParameterFromScanner {

    private float weight;
    private float distance;
    private Scanner scan;
    private final static String WEIGT = "Enter weight";
    private final static String DICTANCE = "Enter distance";

    {
        scan = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public ParameterFromScanner() {
        this.weight = setParameter(WEIGT);
        this.distance = setParameter(DICTANCE);
    }

    public float getWeight() {
        return this.weight;
    }

    public float getDistance() {
        return this.distance;
    }

    public float setParameter(String text) {
        System.out.println(text);
        while (!scan.hasNextFloat()) {
            System.out.println("Enter correct value");
            scan.nextLine();
        }
        float value = scan.nextFloat();
        if (value < 0) {
            throw new IllegalArgumentException("The value can't be negative");
        }
        return value;
    }
}
