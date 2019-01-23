package com.epam.brest.course.runner;

import com.epam.brest.course.count.CountDeliveryCost;
import com.epam.brest.course.createParams.CreateDeliveryKoef;
import com.epam.brest.course.createParams.DeliveryKoefJson;
import com.epam.brest.course.createParams.DeliveryKoefXml;
import com.epam.brest.course.createParams.ScannerDeliveryParams;

import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ScannerDeliveryParams param = new ScannerDeliveryParams();
        CreateDeliveryKoef item = new DeliveryKoefXml(param.getWeight(), "koef.xml");
        CountDeliveryCost count = new CountDeliveryCost(param.getDistanc(), param.getWeight(), item);
        System.out.println(count.toString());

    }
}
