package com.epam.brest.course;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DeliveryKoefJson implements ICreateDeliveryParams {

    private double weight;
    private double koef;

    DeliveryKoefJson(double weight) {

        this.weight = weight;
        setKoef();
    }

    public double getKoef() {
        return this.koef;
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
            if (this.koef == 0) {
                System.out.println("Max weight can be: 500 kg");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
