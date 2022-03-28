package com.mpdv.formatter;

import com.mpdv.model.Parameter;
import com.mpdv.model.Request;

public class RequestFormatter {
    private final Request request;
    private final ParameterFormatter parameterFormatter;

    public RequestFormatter(Request request) {
        this.request = request;
        this.parameterFormatter =
                    new DateTimeParameterFormatter(
                            new StringParameterFormatter(
                                    new BooleanFormatter(
                                        new DefaultParameterFormatter())));
    }

    public String format() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Then execute JTP request \"")
                .append(request.getName())
                .append("\"");

        if (request.getParameters().size() == 0) {
            return stringBuilder.toString();
        }

        stringBuilder.append(" with params \"{\"params\":[");

        for (Parameter parameter : request.getParameters()) {
            stringBuilder
                    .append("{\"acronym\":\"")
                    .append(parameter.getName().replace(".param",""))
                    .append("\",\"value\":")
                    .append(parameterFormatter.format(parameter))
                    .append("}")
                    .append(",");
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append("]}\"");
        return stringBuilder.toString();
    }
}
