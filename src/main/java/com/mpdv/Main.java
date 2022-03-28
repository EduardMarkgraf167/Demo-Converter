package com.mpdv;

import com.mpdv.builder.RequestBuilder;
import com.mpdv.formatter.RequestFormatter;
import com.mpdv.model.Request;
import org.apache.commons.cli.*;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputPath;
        String outputPath;

        try {
            CommandLine commandLine = handleCommandLineArguments(args);
            inputPath = commandLine.getOptionValue("input");
            outputPath = commandLine.getOptionValue("output");
        } catch (ParseException e) {
            System.out.println("Arguments invalid");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        String input = readFile(inputPath);

        List<Request> requests = new RequestBuilder(input).BuildRequests();
        StringBuilder stringBuilder = new StringBuilder();
        for (Request request : requests) {
            stringBuilder
                    .append(new RequestFormatter(request).format())
                    .append(System.lineSeparator());
        }

        String requestString = stringBuilder.toString();
        writeOutputFile(outputPath, requestString);
        System.out.println(requestString);
    }

    private static CommandLine handleCommandLineArguments(String[] args) throws org.apache.commons.cli.ParseException, IllegalArgumentException {
        Options options = new Options();
        options.addOption("input", true, "Absolute path of MOC log file to read from");
        options.addOption("output", true, "Absolute path for output file");
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(options, args);

        if (!commandLine.hasOption("input")) {
            throw new IllegalArgumentException("input is not specified");
        } else if (!commandLine.hasOption("output")) {
            throw new IllegalArgumentException("output is not specified");
        }

        File inputFile = new File(commandLine.getOptionValue("input"));
        if (!inputFile.exists() || !inputFile.isFile()) {
            throw new IllegalArgumentException("input file not found.");
        }

        File outputFile = new File(commandLine.getOptionValue("output"));
        File parentDir = new File(outputFile.getParent());
        if (!parentDir.exists() || !parentDir.isDirectory()) {
            throw new IllegalArgumentException("output file can not be used. Parent directory does not exist.");
        }

        return commandLine;
    }

    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream inputStream = new FileInputStream(path);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void writeOutputFile(String path, String content) {
        try (PrintWriter printWriter = new PrintWriter(path)) {
            printWriter.println(content);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
