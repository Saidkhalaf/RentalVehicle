package be.kdg.rentalVehicleProject.parsing;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import be.kdg.rentalVehicleProject.model.VehicleType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class RentalVehiclesDomParser {
    public static RentalVehicles domReadXML(String fileName) throws ParserConfigurationException, IOException, SAXException {
        RentalVehicles rentalVehicles = new RentalVehicles();

        // Beetje random stuff om een DOM te maken
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = documentBuilder.parse(new File(fileName));
        Element rootElement = doc.getDocumentElement();

        // Alle rentalVehicles ophalen
        NodeList rentalVehiclesList = rootElement.getChildNodes();
        for (int i = 0; i < rentalVehiclesList.getLength(); i++) {
            // Per vehicle de attributen ophalen
            if (rentalVehiclesList.item(i).getNodeType() != Node.ELEMENT_NODE) continue;
            Element e = (Element) rentalVehiclesList.item(i);

            rentalVehicles.add(new RentalVehicle(
                    e.getAttribute( "model"),
                    Double.parseDouble(getPropertyValue(e, "daily-price")),
                    Integer.parseInt(getPropertyValue(e, "aantal-persons")),
                    VehicleType.valueOf(getPropertyValue(e, "vehicle-type")),
                    LocalDate.parse(getPropertyValue(e, "manufacture-date")),
                    getPropertyValue(e,"vehicle-reg-nr")
            ));
        }
        return rentalVehicles;
    }

    private static String getPropertyValue(Element e, String propertyName ) {
        return ((Element) e.getElementsByTagName(propertyName).item(0)).getTextContent();
    }
}
