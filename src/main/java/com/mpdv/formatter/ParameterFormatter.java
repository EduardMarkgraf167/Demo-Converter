package com.mpdv.formatter;

import com.mpdv.model.Parameter;

public abstract class ParameterFormatter {
    private final ParameterFormatter next;
    private final String type;

    protected ParameterFormatter(ParameterFormatter next, String type) {
        this.next = next;
        this.type = type;
    }

    public boolean canHandle(Parameter parameter) {
        if (parameter == null) {
            return  false;
        }

        return parameter.getType().equals(type) || type.equals("default");
    }

    public String format(Parameter parameter) {
        if (!canHandle(parameter)) {
            return next.format(parameter);
        }

        return applyFormat(parameter);
    }

    public abstract String applyFormat(Parameter parameter);
}
