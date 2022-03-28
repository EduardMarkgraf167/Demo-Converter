package com.mpdv.formatter;

import com.mpdv.model.Parameter;

public class BooleanFormatter extends ParameterFormatter {

    protected BooleanFormatter(ParameterFormatter next) {
        super(next, "boolean");
    }

    @Override
    public String applyFormat(Parameter parameter) {
        return parameter.getValue().toLowerCase();
    }
}
