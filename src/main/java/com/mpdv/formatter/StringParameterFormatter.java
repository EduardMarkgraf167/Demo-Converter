package com.mpdv.formatter;

import com.mpdv.model.Parameter;

public class StringParameterFormatter extends ParameterFormatter {

    protected StringParameterFormatter(ParameterFormatter next) {
        super(next, "string");
    }

    @Override
    public String applyFormat(Parameter parameter) {
        return "\"" + parameter.getValue() + "\"";
    }
}
