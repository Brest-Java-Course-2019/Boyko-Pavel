package com.epam.brest.course.calculator;

import org.opentest4j.AssertionFailedError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class CountDeliveryCostTest {

    DataItem parameter = new DataItem(1,2,3);
    CountDeliveryCost count = new CountDeliveryCost(parameter.getDistance(), parameter.getWeight(), parameter.getCoefficient());
    @Test
    void costDeliveyCorrect() {

        Assertions.assertEquals(new BigDecimal("6"), count.costDelivey());
    }

    @Test
    void costDeliveyUncorrect() {

        Assertions.assertThrows(AssertionFailedError.class, () -> {
            Assertions.assertEquals(new BigDecimal("8"), count.costDelivey());
            });
    }
}