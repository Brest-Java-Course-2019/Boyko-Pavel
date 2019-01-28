package com.epam.brest.course.createParams;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class DeliveryKoefXmlImpl extends ReadingFile implements CreateDeliveryKoef {
    private double weight;
    private double koef;
    private String filePath;

    public DeliveryKoefXmlImpl(double weight, String fileName) throws IOException {

        this.weight = weight;
        this.filePath = filePath(fileName);
        setKoef();
    }

    public double getKoef() {
        return this.koef;
    }

     public void setKoef() {
        try {
            File fXmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("group");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                Element eElement = (Element) nNode;
                double start = Double.parseDouble(eElement.getElementsByTagName("start").item(0).getTextContent());
                double end = Double.parseDouble(eElement.getElementsByTagName("end").item(0).getTextContent());
                double koef = Double.parseDouble(eElement.getElementsByTagName("koef").item(0).getTextContent());
                if (this.weight >= start && this.weight < end) {
                    this.koef = koef;
                }
            }
            if ((this.koef == 0)) {
                System.out.println("max Weight can be 5000kg, min weight 1 kg");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
