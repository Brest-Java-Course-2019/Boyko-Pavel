package com.epam.brest.course;

import java.util.Scanner;

public class ScannerDeliveryParams {

    private double weight;
    private double distanc;

    ScannerDeliveryParams() {
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
        Scanner scan = new Scanner(System.in);
        System.out.println(text);
        while (!scan.hasNextDouble()) {
            System.out.println("Enter correct value");
            scan.nextLine();
        }
        return scan.nextDouble();
    }
}
