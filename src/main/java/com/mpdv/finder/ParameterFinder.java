package com.mpdv.finder;

import com.mpdv.model.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParameterFinder extends Finder<Parameter> {
    private static final String expression = "(?<ParameterName>\\S*), (?<ParameterValue>[^,]+?), (?<ParameterType>\\S+)";

    public ParameterFinder(String input) {
        super(expression, input);
    }

    @Override
    public List<Parameter> find() {
        List<Parameter> parameters = new ArrayList<>();
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(getInput());
        while(matcher.find()) {
            String parameterName = matcher.group("ParameterName");
            String parameterValue = matcher.group("ParameterValue");
            String parameterType = matcher.group("ParameterType").replace("\\r", "").replace("\\n","");
            Parameter parameter = new Parameter(parameterName, parameterValue, parameterType);
            parameters.add(parameter);
        }

        return parameters.stream().filter(x -> x.getName().endsWith("param")).collect(Collectors.toList());
    }
}
