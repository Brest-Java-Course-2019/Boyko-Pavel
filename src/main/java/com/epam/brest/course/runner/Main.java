package com.epam.brest.course.runner;

import com.epam.brest.course.count.CountDeliveryCost;
import com.epam.brest.course.createParams.CreateDeliveryKoef;
import com.epam.brest.course.createParams.DeliveryKoefXmlImpl;
import com.epam.brest.course.createParams.ScannerDeliveryParams;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        ScannerDeliveryParams param = new ScannerDeliveryParams();
        CreateDeliveryKoef item = new DeliveryKoefXmlImpl(param.getWeight(), "koef.xml");
        CountDeliveryCost count = new CountDeliveryCost(param.getDistanc(), param.getWeight(), item);
        System.out.println(count.toString());

    }
}
