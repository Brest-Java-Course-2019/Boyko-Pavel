package com.epam.brest.course;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DeliveryCost {

    private double weight;
    private double distans;
    private Scanner scan;
    private double koef;

    {
        scan = new Scanner(System.in);
    }

    public DeliveryCost() {
        this.weight = scan("Enter weight");
        this.distans = scan("Enter distans");
        setKoef();
    }

    public void costDelivey() {

        double value = this.distans * this.weight * this.koef;
        System.out.printf("Cost delivery: %.3f $", value);
    }

    public double scan(String text) {

        System.out.println(text);
        while (!this.scan.hasNextDouble()) {
            System.out.println("Enter correct value");
            this.scan.nextLine();
        }
        return this.scan.nextDouble();
    }

    public void setKoef() {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("koef.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("koefs");
            for (Object koefItem : jsonArray) {
                JSONObject item = (JSONObject) koefItem;
                Double start = (Double) item.get("start");
                Double end = (Double) item.get("end");
                Double koef = (Double) item.get("koef");
                if (this.weight >= start & this.weight < end) {
                    this.koef = koef;
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
