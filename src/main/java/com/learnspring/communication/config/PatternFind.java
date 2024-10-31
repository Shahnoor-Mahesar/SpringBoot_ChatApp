package com.learnspring.communication.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PatternFind {

    public static void main(String[] args) {

        // Example input code
        String inputCode = "int x = 10 ;";

        // Step 1: Tokenize the input string
        StringTokenizer tokens = tokenize(inputCode);

        String[] tokenss = tokenizerToArray(tokens);

        // Print the result
        for (String token : tokenss) {
            System.out.println(token);
        }



//        System.out.println(Arrays.stream(tokens).toList());

        // Step 2: Validate tokens for data types, variable names, and assignments
        if (validateSyntax(tokenss)) {
            System.out.println("Code syntax is valid!");

            // Step 3: Generate the symbol table
            Map<String, SymbolInfo> symbolTable = generateSymbolTable(tokenss);
            System.out.println("Symbol Table: " + symbolTable);
        } else {
            System.out.println("Code syntax is invalid!");
        }
    }


    public static String[] tokenizerToArray(StringTokenizer tokenizer) {
        // Create a dynamic list to store the tokens
        int tokenCount = tokenizer.countTokens();
        String[] tokens = new String[tokenCount];
        int index = 0;

        // Iterate through the StringTokenizer and add each token to the array
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim(); // Optional: trim whitespace
            tokens[index++] = token;
        }

        return tokens;
    }
    // Tokenizer method: splits the input string into tokens
    public static StringTokenizer tokenize(String inputCode) {
        // Trim the input to avoid leading/trailing whitespace issues
        inputCode = inputCode.trim();

        StringTokenizer st = new StringTokenizer(inputCode," ");
        // Split by spaces, symbols like '=', and ';' but not by ';' itself
        return st;
    }

    // Validate syntax and constraints on the tokens
    public static boolean validateSyntax(String[] tokens) {
        // Check if the code has at least 5 tokens (data type, variable name, assignment operator, value, semicolon)
        if (tokens.length < 5) {
            System.out.println("Insufficient tokens in the code.");
            return false;
        }

        // Validate data type
        if (!isValidDataType(tokens[0])) {
            System.out.println("Invalid data type: " + tokens[0]);
            return false;
        }

        // Validate variable name
        if (!isValidVariableName(tokens[1])) {
            System.out.println("Invalid variable name: " + tokens[1]);
            return false;
        }

        // Validate assignment operator
        if (!tokens[2].equals("=")) {
            System.out.println("Expected assignment operator '=', found: " + tokens[2]);
            return false;
        }

        // Validate value (for simplicity, assuming it's an integer for now)
        if (!isValidValue(tokens[3], tokens[0])) {
            System.out.println("Invalid value: " + tokens[3]);
            return false;
        }

        // Check if the statement ends with a semicolon
        if (!tokens[4].equals(";")) {
            System.out.println("Missing semicolon at the end.");
            return false;
        }

        // If all checks pass, return true
        return true;
    }

    // Check if the given token is a valid data type (int, float, double, etc.)
    public static boolean isValidDataType(String token) {
        String[] validDataTypes = {"int", "float", "double", "String", "boolean", "char"};
        for (String dataType : validDataTypes) {
            if (token.equals(dataType)) {
                return true;
            }
        }
        return false;
    }

    // Check if the given token is a valid variable name
    public static boolean isValidVariableName(String token) {
        // Variable names must start with a letter and can only contain letters, digits, and underscores
        return token.matches("^[a-zA-Z_][a-zA-Z0-9_]*$");
    }

    // Check if the given token is a valid value for the specified data type
    public static boolean isValidValue(String token, String dataType) {
        switch (dataType) {
            case "int":
                return token.matches("^[0-9]+$");
            case "float":
            case "double":
                return token.matches("^[0-9]+\\.[0-9]+$");
            case "boolean":
                return token.equals("true") || token.equals("false");
            case "char":
                return token.matches("^'.{1}'$"); // Single character enclosed in single quotes
            case "String":
                return token.matches("^\".*\"$"); // Any text enclosed in double quotes
            default:
                return false;
        }
    }

    // Function to generate a symbol table from the tokens
    public static Map<String, SymbolInfo> generateSymbolTable(String[] tokens) {
        Map<String, SymbolInfo> symbolTable = new HashMap<>();

        // Assuming valid syntax; adding the variable name, type, and value to the symbol table
        String dataType = tokens[0];
        String variableName = tokens[1];
        String value = tokens[3];

        // Add variable information to the symbol table
        SymbolInfo symbolInfo = new SymbolInfo(dataType, value);
        symbolTable.put(variableName, symbolInfo);

        return symbolTable;
    }

    // A class to store information about each symbol (variable) in the symbol table
    static class SymbolInfo {
        String dataType;
        String value;

        SymbolInfo(String dataType, String value) {
            this.dataType = dataType;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{dataType: " + dataType + ", value: " + value + "}";
        }
    }
}
