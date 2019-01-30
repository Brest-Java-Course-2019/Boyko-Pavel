package com.epam.brest.course.createParams;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class CoefficientFromJsonFileImpl implements CreateDeliveryCoefficient {

    private float weight;
    private float coefficient;
    private static final String TAG_GROUPS = "groups";
    private static final String TAG_COEFFICIENT = "coefficient";
    private static final String TAG_START = "start";
    private static final String TAG_END = "end";
    private static final Logger LOGGER = LogManager.getLogger();

    public CoefficientFromJsonFileImpl(float weight) {
        this.weight = weight;
    }

    @Override
    public void setCoefficientFromFile(String filePath) {
        LOGGER.debug(filePath);
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(filePath));
            JSONArray jsonArray = (JSONArray) jsonObject.get(TAG_GROUPS);
            for (Object coefficientItem : jsonArray) {
                JSONObject item = (JSONObject) coefficientItem;
                Float start = (Float) item.get(TAG_START);
                Float end = (Float) item.get(TAG_END);
                Float coefficient = (Float) item.get(TAG_COEFFICIENT);
                if (this.weight >= start && this.weight < end) {
                    this.coefficient = coefficient;
                }
            }
            jsonObject.clear();
            if (this.coefficient == 0) {
                System.out.println("Weight can be: (1 - 5000) kg ");
            }
        } catch (ParseException | IOException e) {
            LOGGER.error("Incorrect parse data. Error: [{}]", e);
        }
    }

    public float getCoefficient() {
        return this.coefficient;
    }
}

