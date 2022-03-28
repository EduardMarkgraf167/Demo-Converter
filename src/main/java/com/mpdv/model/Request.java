package com.mpdv.model;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private final String name;
    private final String rawBody;
    private final List<Parameter> parameters;

    public Request(String name, String rawBody) {
        this.name = name;
        this.rawBody = rawBody;
        this.parameters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRawBody() {
        return rawBody;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }
}
