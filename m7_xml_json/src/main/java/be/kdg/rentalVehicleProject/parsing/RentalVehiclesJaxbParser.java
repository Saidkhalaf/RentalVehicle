package be.kdg.rentalVehicleProject.parsing;

import be.kdg.rentalVehicleProject.model.RentalVehicles;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class RentalVehiclesJaxbParser {

    public static void JaxbWriteXml(String file, Object root) {
        try {
            JAXBContext context = JAXBContext.newInstance(RentalVehicles.class);
            Marshaller jaxbMarshaller = context.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(root, new File(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) {

        try {
            JAXBContext context = JAXBContext.newInstance(typeParameterClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File fileToProcess = new File(file);
            return (T) unmarshaller.unmarshal(fileToProcess);
        } catch(JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
