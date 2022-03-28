package com.mpdv.formatter;

import com.mpdv.model.Parameter;

public class DefaultParameterFormatter extends ParameterFormatter {

    protected DefaultParameterFormatter() {
        super(null, "default");
    }

    @Override
    public String applyFormat(Parameter parameter) {
        return parameter.getValue();
    }
}
