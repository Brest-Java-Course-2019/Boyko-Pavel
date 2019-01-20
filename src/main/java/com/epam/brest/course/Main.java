package com.epam.brest.course;

public class Main {

    public static void main(String[] args) {

        ScannerDeliveryParams param = new ScannerDeliveryParams();
        ICreateDeliveryParams item = new DeliveryKoefXml(param.getWeight());
        CountDeliveryCost count = new CountDeliveryCost(param.getDistanc(), param.getWeight(), item);
        System.out.println(count.toString());
    }
}
