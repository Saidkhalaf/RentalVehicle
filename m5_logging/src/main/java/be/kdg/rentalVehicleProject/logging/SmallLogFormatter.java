package be.kdg.rentalVehicleProject.logging;


import java.text.MessageFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SmallLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format(
                "%s Level: %s melding %s\n",
                Instant.ofEpochMilli(record.getMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
                record.getLevel(),
                MessageFormat.format(record.getMessage(), record.getParameters())
        );
    }
}
