package com.epam.brest.course.calculator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class CountDeliveryCost {

    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal coefficient;
    public final Logger LOGGER = LogManager.getLogger();

    public CountDeliveryCost(BigDecimal distance, BigDecimal weight, BigDecimal coefficient) {

        this.distance = distance;
        this.weight = weight;
        this.coefficient = coefficient;
        LOGGER.log(Level.INFO, this::toString);
    }

    public BigDecimal costDelivey() {

        return distance.multiply(weight.multiply(coefficient));
    }

    @Override
    public String toString() {
        return "Cost delivey(" +
                "distance(km): " + this.distance +
                " weigt: " + this.weight + ") - "
                + String.format("%.3f", costDelivey()) + "$";
    }
}

