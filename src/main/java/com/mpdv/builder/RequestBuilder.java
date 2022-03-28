package com.mpdv.builder;

import com.mpdv.finder.ParameterFinder;
import com.mpdv.finder.RequestFinder;
import com.mpdv.model.Parameter;
import com.mpdv.model.Request;

import java.util.List;

public class RequestBuilder {
    private final String input;

    public RequestBuilder(String input) {
        this.input = input;
    }

    public List<Request> BuildRequests() {
        List<Request> requests = new RequestFinder(input).find();

        for (Request request : requests) {
            List<Parameter> parameters = new ParameterFinder(request.getRawBody()).find();
            request.getParameters().addAll(parameters);
        }

        return requests;
    }
}
