package com.epam.brest.course;

import java.util.Scanner;

public class DeliveryCost {

    public void costDelivey() {

        long val = scanDistanse() * scanWeight() * 4;
        System.out.printf("Cost delivery: %d $", val);

    }

    public long scanWeight() {
        System.out.println("Enter weigh");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextLong()) {
            System.out.println("Enter correct weigh");
            scan = new Scanner(System.in);

        }
        return scan.nextLong();
    }

    public long scanDistanse() {

        System.out.println("Enter  dictance");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextLong()) {
            System.out.println("Enter correct dictance");
            scan = new Scanner(System.in);
        }
        return scan.nextLong();
    }

}
