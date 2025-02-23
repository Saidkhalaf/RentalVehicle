package be.kdg.rentalVehicleProject.adapter;


import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

/**
 * This class adapts the LocalDate objects by implementing the XmlAdapter interface in order that
 * JaxB is capable of marshal and unmarshal them
 */
public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String myString) throws Exception {
        return LocalDate.parse(myString);
    }

    public String marshal(LocalDate myDate) throws Exception {
        return myDate.toString();
    }



}
