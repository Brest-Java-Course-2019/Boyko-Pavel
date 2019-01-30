package com.epam.brest.course.menu;

import com.epam.brest.course.calculator.CountDeliveryCost;
import com.epam.brest.course.calculator.DataItem;
import com.epam.brest.course.createParams.DeliveryCoefficient;
import com.epam.brest.course.createParams.ParameterFromScanner;
import com.epam.brest.course.createParams.FilePath;
import com.epam.brest.course.createParams.FromXmlFileImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;


public class UserConsoleMenu {

    private static final String FILE_NAME = "coefficient.xml";
    private static final Logger LOGGER = LogManager.getLogger();


    public void showMenu(){

        ParameterFromScanner parameterDelivery = new ParameterFromScanner();
        FilePath filePath = new FilePath();
        try {
        DeliveryCoefficient coefficient = new FromXmlFileImpl(parameterDelivery.getWeight());
        coefficient.setCoefficientFromFile(filePath.getFilePath(FILE_NAME));
        DataItem parametrsOfDelivery = new DataItem(parameterDelivery.getDistance(),
                parameterDelivery.getWeight(), coefficient.getCoefficient());
        CountDeliveryCost costOfDelivery = new CountDeliveryCost(parametrsOfDelivery.getDistance(),
                parametrsOfDelivery.getWeight(), parametrsOfDelivery.getCoefficient());
        System.out.println(costOfDelivery.toString());
        } catch (FileNotFoundException e) {
            LOGGER.error("File don't found", e.getMessage());
        }
    }
}
