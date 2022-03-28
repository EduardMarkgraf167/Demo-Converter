package com.mpdv.finder;

import java.util.List;

public abstract class Finder<T> {
    private final String expression;
    private final String input;

    public Finder(String expression, String input) {
        this.expression = expression;
        this.input = input;
    }

    public abstract List<T> find();

    public String getExpression() {
        return expression;
    }

    public String getInput() {
        return input;
    }
}
