package com.mpdv.finder;

import com.mpdv.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestFinder extends Finder<Request> {
    private static final String expression = "(?<RawBody>.*\\|RequestData\\.( ApplicationId: \\S+)? <(?<RequestName>.+?) .+?called using parameters(\\s+\\S*,.*,.*)*)";

    public RequestFinder(String input) {
        super(expression, input);
    }

    @Override
    public List<Request> find() {
        List<Request> requests = new ArrayList<>();
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(getInput());
        while(matcher.find()) {
            String requestName = matcher.group("RequestName");
            String rawBody = matcher.group("RawBody");
            Request request = new Request(requestName, rawBody);
            requests.add(request);
        }

        return requests;
    }
}
