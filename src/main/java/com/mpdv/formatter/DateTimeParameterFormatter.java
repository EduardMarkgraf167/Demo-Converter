package com.mpdv.formatter;

import com.mpdv.model.Parameter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParameterFormatter extends ParameterFormatter {
    private static final String dateTimeFormat = "MM/dd/yyyy HH:mm:ss";

    protected DateTimeParameterFormatter(ParameterFormatter next) {
        super(next, "datetime");
    }

    @Override
    public String applyFormat(Parameter parameter) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.parse(parameter.getValue(), DateTimeFormatter.ofPattern(dateTimeFormat));
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
        String textValue = zonedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssZ")).replace(" ","T");
        textValue = textValue.substring(0, textValue.length()-2) + ":00";
        return "\"" + textValue + "\"";
    }
}
