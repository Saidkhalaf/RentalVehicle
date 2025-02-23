package be.kdg.rentalVehicleProject.parsing;

import be.kdg.rentalVehicleProject.data.Data;
import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final RentalVehicles rentalVehicles = new RentalVehicles();

    @BeforeEach
    void setUp() {
        Data.getData().forEach(rentalVehicles::add);
    }

    @Test
    public void testStaxDom() {
        RentalVehiclesStaxParser staxParser =new RentalVehiclesStaxParser(rentalVehicles, "datafiles/rentalVehiclesStax.xml");
        assertDoesNotThrow(() -> staxParser.staxWriteXML());

        RentalVehicles readFormXml =null;
        try {
            readFormXml =RentalVehiclesDomParser.domReadXML("datafiles/rentalVehiclesStax.xml");
        }catch (ParserConfigurationException e){
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

        List<RentalVehicle> rentalVehicleslist =rentalVehicles.getRentalVehicles().stream().toList();
        List<RentalVehicle> readFromXmlList = readFormXml.getRentalVehicles().stream().toList();

        assertAll(
                () -> assertEquals(rentalVehicleslist.get(1), readFromXmlList.get(1), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleslist.get(2), readFromXmlList.get(2), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleslist.get(5), readFromXmlList.get(5), "De vehicles moeten gelijk zijn")
        );
    }

    @Test
    public void testJaxb() {

        // Write rentalVehicles to XML file
        RentalVehiclesJaxbParser.JaxbWriteXml("datafiles/rentalVehiclesJaxb.xml", rentalVehicles);
        // Read data from the XML file
        RentalVehicles readFromXml = RentalVehiclesJaxbParser.JaxbReadXml("datafiles/rentalVehiclesJaxb.xml", RentalVehicles.class);

        List<RentalVehicle> rentalVehicleList = rentalVehicles.getRentalVehicles().stream().toList();
        System.out.println(rentalVehicleList);
        List<RentalVehicle> readFromXmlList = readFromXml.getRentalVehicles().stream().toList();
        System.out.println(readFromXmlList);

        assertAll(
                () -> assertEquals(rentalVehicles.getRentalVehicles(), readFromXml.getRentalVehicles(), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleList.get(2), readFromXmlList.get(2), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleList.get(5), readFromXmlList.get(5), "De vehicles moeten gelijk zijn")
        );
    }

    @Test
    public void testGson() {
        RentalVehiclesGsonParser.writeJson(rentalVehicles, "datafiles/rentalVehiclesGson.json");
        RentalVehicles readFromJson = RentalVehiclesGsonParser.readJson("dataFiles/rentalVehiclesGson.json");

        List<RentalVehicle> rentalVehicleList = rentalVehicles.getRentalVehicles().stream().toList();
        List<RentalVehicle> readFromJsonList = readFromJson.getRentalVehicles().stream().toList();

        assertAll(
                () -> assertEquals(rentalVehicleList.get(0), readFromJsonList.get(0), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleList.get(2), readFromJsonList.get(2), "De vehicles moeten gelijk zijn"),
                () -> assertEquals(rentalVehicleList.get(5), readFromJsonList.get(5), "De vehicles moeten gelijk zijn")
        );

    }
}