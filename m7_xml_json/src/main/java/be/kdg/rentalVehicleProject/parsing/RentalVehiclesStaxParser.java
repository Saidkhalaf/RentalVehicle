package be.kdg.rentalVehicleProject.parsing;

import be.kdg.rentalVehicleProject.model.RentalVehicle;
import be.kdg.rentalVehicleProject.model.RentalVehicles;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RentalVehiclesStaxParser {


    private RentalVehicles rentalVehicles;
    private XMLStreamWriter streamWriter;

    public RentalVehiclesStaxParser(RentalVehicles rentalVehicles, String filePath) {
        this.rentalVehicles = rentalVehicles;

        try{
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath)));

            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter streamWriter = xmlOutputFactory.createXMLStreamWriter(printWriter);

            this.streamWriter = new IndentingXMLStreamWriter(streamWriter);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    public void staxWriteXML() throws XMLStreamException {
        streamWriter.writeStartDocument();
        streamWriter.writeStartElement("rentalVehicles");

        this.rentalVehicles.getRentalVehicles().forEach(this::writeElement);

        streamWriter.writeEndElement();
        streamWriter.writeEndDocument();

        streamWriter.flush();
        streamWriter.close();
    }

    private void writeElement(RentalVehicle rentalVehicle){
        try {
            streamWriter.writeStartElement("vehicle");
            streamWriter.writeAttribute("model", rentalVehicle.getModel());

            //rentalPricePerDay
            streamWriter.writeStartElement("daily-price");
            streamWriter.writeCharacters(Double.toString(rentalVehicle.getRentalPricePerDay()));
            streamWriter.writeEndElement();

            //aantalPersons
            streamWriter.writeStartElement("aantal-persons");
            streamWriter.writeCharacters(Integer.toString(rentalVehicle.getAantalPersons()));
            streamWriter.writeEndElement();

            //Enum
            streamWriter.writeStartElement("vehicle-type");
            streamWriter.writeCharacters(rentalVehicle.getVehicleType().toString());
            streamWriter.writeEndElement();

            //manufactureDate
            streamWriter.writeStartElement("manufacture-date");
            streamWriter.writeCharacters(rentalVehicle.getManufactureDate().toString());
            streamWriter.writeEndElement();

            //VehicleRegNr
            streamWriter.writeStartElement("vehicle-reg-nr");
            streamWriter.writeCharacters(rentalVehicle.getVehicleRegNr());
            streamWriter.writeEndElement();

            streamWriter.writeEndElement();
        }catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

}
