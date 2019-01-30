package com.epam.brest.course.menu;

import com.epam.brest.course.calculator.CountDeliveryCost;
import com.epam.brest.course.calculator.TransformationParametrs;
import com.epam.brest.course.createParams.CreateDeliveryCoefficient;
import com.epam.brest.course.createParams.DeliveryParamsFromScanner;
import com.epam.brest.course.createParams.FilePath;
import com.epam.brest.course.createParams.СoefficientFromXmlFileImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;


public class UserConsoleMenu {

    private static final String FILE_NAME = "coefficient.xml";
    private static final Logger LOGGER = LogManager.getLogger();


    public void showMenu(){

        DeliveryParamsFromScanner parameterDelivery = new DeliveryParamsFromScanner();
        FilePath filePath = new FilePath();
        try {
        CreateDeliveryCoefficient coefficient = new СoefficientFromXmlFileImpl(parameterDelivery.getDistance());
        coefficient.setCoefficientFromFile(filePath.getFilePath(FILE_NAME));
        TransformationParametrs parametrsOfDelivery = new TransformationParametrs(parameterDelivery.getDistance(), parameterDelivery.getWeight(), coefficient.getCoefficient());
        CountDeliveryCost costOfDelivery = new CountDeliveryCost(parametrsOfDelivery.getDistance(), parametrsOfDelivery.getWeight(), parametrsOfDelivery.getCoefficient());
        System.out.println(costOfDelivery.toString());
        } catch (FileNotFoundException e) {
            LOGGER.error("File don't found", e.getMessage());
        }
    }
}
