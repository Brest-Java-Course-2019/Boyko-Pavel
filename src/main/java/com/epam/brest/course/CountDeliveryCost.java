package com.epam.brest.course;


public class CountDeliveryCost {

    private double weight;
    private double distans;
    private double koef;

    CountDeliveryCost(double distans, double weight, ICreateDeliveryParams koef) {
        this.distans = distans;
        this.weight = weight;
        this.koef = koef.getKoef();
    }

    public double costDelivey() {

        return this.distans * this.weight * this.koef;
    }

    @Override
    public String toString() {
        return "Cost delivey :" + String.format("%.3f", costDelivey()) + "$";
    }
}
