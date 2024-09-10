package com.learnspring.communication.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternFind {

//    public static void main(String[] args) {
//        String code = "int int _x = 10;"; // Example code to be checked
//
//
//        String dataTypePattern = "\\b(int|float|double|char)\\b";
//        String variableNamePattern = "[a-zA-Z_][a-zA-Z0-9_]*";
//        String assignmentPattern = "=";
//        String integerPattern = "\\d+";
//
//
//        Pattern dataTypeRegex = Pattern.compile(dataTypePattern);
//        Pattern variableNameRegex = Pattern.compile(variableNamePattern);
//        Pattern assignmentRegex = Pattern.compile(assignmentPattern);
//        Pattern integerRegex = Pattern.compile(integerPattern);
//
//        // Tokenize the code
//        String[] tokens = code.split("\\s+|;|=");
//        for (String token : tokens) {
//            // Check for data type
//            if (dataTypeRegex.matcher(token).matches()) {
//                System.out.println("Data type: " + token);
//            }
//            // Check for variable name
//            else if (variableNameRegex.matcher(token).matches()) {
//                System.out.println("Variable name: " + token);
//            }
//            // Check for integer value
//            else if (integerRegex.matcher(token).matches()) {
//                System.out.println("Integer value: " + token);
//            }
//            // Check for assignment operator
//            else if (assignmentRegex.matcher(token).matches()) {
//                System.out.println("Assignment operator: " + token);
//            }
//        }
//
//        // Further validation could be done here, like checking if variable names are valid
//        // and if the assignment matches the declared type.
//    }
}
