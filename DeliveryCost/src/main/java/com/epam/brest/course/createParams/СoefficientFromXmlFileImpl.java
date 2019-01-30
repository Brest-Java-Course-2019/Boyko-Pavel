package com.epam.brest.course.createParams;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class СoefficientFromXmlFileImpl implements CreateDeliveryCoefficient {
    private float weight;
    private float coefficient;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TAG_GROUP = "group";
    private static final String TAG_COEFFICIENT = "coefficient";
    private static final String TAG_START = "start";
    private static final String TAG_END = "end";

    public СoefficientFromXmlFileImpl(Float weight) {
        LOGGER.debug(weight);
        this.weight = weight;
    }

    @Override
    public float getCoefficient() {
        LOGGER.debug(this.coefficient);
        return this.coefficient;
    }

    @Override
    public void setCoefficientFromFile(String filePath) {
        try {
            LOGGER.debug(filePath);
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(TAG_GROUP);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                float start = Float.parseFloat(eElement.getElementsByTagName(TAG_START).item(0).getTextContent());
                float end = Float.parseFloat(eElement.getElementsByTagName(TAG_END).item(0).getTextContent());
                float coefficient = Float.parseFloat(eElement.getElementsByTagName(TAG_COEFFICIENT).item(0).getTextContent());
                if (this.weight >= start && this.weight < end) {
                    this.coefficient = coefficient;
                }
            }
            if ((this.coefficient == 0)) {
                System.out.println("max Weight can be 5000kg, min weight 1 kg");
            }
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("Incorrect parser configuration: {}", e);
        } catch (IOException e) {
            LOGGER.error("Incorrect parse data. Error: {}", e);
        }
    }
}
