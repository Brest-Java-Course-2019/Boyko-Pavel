package com.epam.brest.course.count;


import com.epam.brest.course.createParams.CreateDeliveryKoef;

public class CountDeliveryCost {

    private double weight;
    private double distans;
    private double koef;

    public CountDeliveryCost(double distans, double weight, CreateDeliveryKoef koef) {
        this.distans = distans;
        this.weight = weight;
        this.koef = koef.getKoef();
    }

    public double costDelivey() {

        return this.distans * this.weight * this.koef;
    }

    @Override
    public String toString() {
        return "Cost delivey(" +
                "distance(km): " + this.distans +
                " weigt: " + this.weight +") - "
                + String.format("%.3f", costDelivey()) + "$";
    }
}
