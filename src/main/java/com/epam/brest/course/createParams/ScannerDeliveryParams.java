package com.epam.brest.course.createParams;

import java.util.Scanner;

public class ScannerDeliveryParams {

    private double weight;
    private double distanc;
    private Scanner scan;

    {
        scan = new Scanner(System.in);
    }

    public ScannerDeliveryParams() {
        this.weight = scanSet("Enter weight");
        this.distanc = scanSet("Enter distance");
    }

    public double getWeight() {
        return weight;
    }

    public double getDistanc() {
        return distanc;
    }

    public double scanSet(String text) {
        System.out.println(text);
        while (!scan.hasNextDouble()) {
            System.out.println("Enter correct value");
            scan.nextLine();
        }
        double value = scan.nextDouble();
        if (value <  0){
            throw new IllegalArgumentException("The value can't be negative");
        }
        return value;
    }
}
