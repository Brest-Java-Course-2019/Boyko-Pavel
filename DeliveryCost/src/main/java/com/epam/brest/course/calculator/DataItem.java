package com.epam.brest.course.calculator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class DataItem {
    private final static Logger LOGGER = LogManager.getLogger();

    private BigDecimal weight;
    private BigDecimal distance;
    private BigDecimal coefficient;

    public DataItem(float weight, float distance, float coefficient) {
        this.weight = new BigDecimal(weight);
        this.distance = new BigDecimal(distance);
        this.coefficient = new BigDecimal(coefficient);
    }

    public void setWeight(BigDecimal weight) {
        LOGGER.debug("Set weight");
        this.weight = weight;
    }

    public void setDistance(BigDecimal distance) {
        LOGGER.debug("Set distance");
        this.distance = distance;
    }

    public void setCoefficient(BigDecimal pricePerKg) {
        LOGGER.debug("Set price per kg");
        this.coefficient = pricePerKg;
    }


    public BigDecimal getWeight() {
        return weight;
    }

    public BigDecimal getDistance() {
        return distance;
    }


    public BigDecimal getCoefficient() {
        return coefficient;
    }

    @Override
    public String toString() {
        return "DataItem{"
                + "weight=" + weight
                + ", distance=" + distance
                + ", coefficient=" + coefficient +
                '}';
    }
}
