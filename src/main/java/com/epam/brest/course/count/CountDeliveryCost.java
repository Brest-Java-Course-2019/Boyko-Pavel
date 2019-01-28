package com.epam.brest.course.count;


import com.epam.brest.course.createParams.CreateDeliveryKoef;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CountDeliveryCost {

    private double weight;
    private double distans;
    private double koef;
    public final Logger LOGGER = LogManager.getLogger();

    public CountDeliveryCost(double distans, double weight, CreateDeliveryKoef koef) {
        this.distans = distans;
        this.weight = weight;
        this.koef = koef.getKoef();
        LOGGER.log(Level.INFO, this::toString);
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
