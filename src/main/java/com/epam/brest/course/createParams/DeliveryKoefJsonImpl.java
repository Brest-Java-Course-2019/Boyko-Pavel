package com.epam.brest.course.createParams;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class DeliveryKoefJsonImpl extends ReadingFile implements CreateDeliveryKoef {

    private double weight;
    private double koef;
    private String fielPath;

    public DeliveryKoefJsonImpl(double weight, String fileName) throws IOException {
        this.weight = weight;
        this.fielPath = filePath(fileName);
    }

    public double getKoef() {
        return this.koef;
    }

    public void setKoef() {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(this.fielPath));
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
                System.out.println("Weight can be: (1- 5000) kg ");
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
